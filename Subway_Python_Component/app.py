from flask import Flask, render_template, request, jsonify
import requests 
import json
from typing import Dict
import os 
from flask_socketio import SocketIO, emit, join_room, leave_room
from database import getData, getDataByID, addRow, editRow, removeRow
from datetime import datetime
import pytz
app = Flask(__name__)
app.config["SECRET_KEY"] = os.environ.get('secret_key')
SPRING_BOOT_API = "http://localhost:8080/api/routes"
socketio = SocketIO(app, cors_allowed_origins="*")

tz = pytz.timezone('Asia/Hong_Kong')

@app.route("/")
def home():
    return render_template('dashboard.html')

def find_routes(start, destination):
    response = requests.get(SPRING_BOOT_API,params = {"param1":start,"param2":destination})
    response = response.text
    response = json.loads(response)
    return response 

@app.route('/find-route', methods=['GET', 'POST'])
def find_route():
    if request.method == 'GET':
        return render_template('find_route.html')
    
    elif request.method == 'POST':
        # Get form data from the HTML form
        start_station = request.form.get('start_station')
        destination_station = request.form.get('destination_station')
        
        # Validate input
        if not start_station or not destination_station:
            return jsonify({
                'error': 'Please select both starting station and destination.'
            }), 400
        
        if start_station == destination_station:
            return jsonify({
                'error': 'Starting station and destination cannot be the same.'
            }), 400
        
        try:
            # Call route finding api endpoint
            routes_data = find_routes(start_station, destination_station)
            
            return jsonify({
                'success': True,
                'start_station': start_station,
                'destination_station': destination_station,
                'routes': routes_data
            })
            
        except Exception as e:
            return jsonify({
                'error': f'Error finding routes: {str(e)}'
            }), 500

@app.route('/announcements', methods = ['POST', 'GET'])
def announcements():
    if request.method == 'GET':
        return render_template("announcements.html")

@app.route("/api/announcements", methods=["GET"])
def get_announcements():
    # API endpoint to get all announcements
    try:
        announcements = getData()
        if announcements:
            announcements_list = list(announcements) if announcements else []
            return jsonify({"success": True, "announcements": announcements_list})
        else:
            return jsonify({"success": True, "announcements": []})
    except Exception as e:
        # print(f"Error fetching announcements: {e}")
        return jsonify({"success": False, "error": str(e)})

active_users: Dict[str, dict] = {}

@socketio.on("message")
def handle_message(data: dict):
    try:
        # print("Received message data:", data)
        
        message = data.get('msg', '').strip()
        author = data.get('author', '').strip()
        
        if not message or not author:
            # print("Missing message or author")
            return 
            
        timestamp = datetime.now(tz).isoformat()
        # print(timestamp)
        try:
            announcement_id = addRow(author, message, timestamp)
            # print(f'Saved announcement to database with ID: {announcement_id}')
        except Exception as db_error:
            # print(f'Database error: {db_error}')
            emit('error', {'message': 'Failed to save announcement'})
            return
        
        # print(f'Broadcasting message from {author}: {message}')
        
        # Broadcast to all connected clients
        emit('announcement', {
            'msg': message,
            'author': author,
            'time': timestamp,
            'id': announcement_id
        }, broadcast=True)
        
    except Exception as e:
        # print(f'Error handling message: {e}')
        import traceback
        traceback.print_exc()

@socketio.on('edit_announcement')
def handle_edit_announcement(data: dict):
    try:
        announcement_id = data.get('id')
        author = data.get('author', '').strip()
        message = data.get('msg', '').strip()
        
        if not announcement_id or not author or not message:
            emit('error', {'message': 'Missing required fields'})
            return
            
        # Update in database
        editRow(id=announcement_id, author=author, message=message)
        
        # Broadcast the update to all clients
        emit('announcement_updated', {
            'id': announcement_id,
            'author': author,
            'msg': message,
            'time': datetime.now(tz).isoformat()
        }, broadcast=True)
        
        # print(f'Updated announcement {announcement_id}')
        
    except Exception as e:
        # print(f'Error editing announcement: {e}')
        emit('error', {'message': 'Failed to edit announcement'})

@socketio.on('delete_announcement')
def handle_delete_announcement(data: dict):
    try:
        announcement_id = data.get('id')
        
        if not announcement_id:
            emit('error', {'message': 'Missing announcement ID'})
            return
            
        # Remove from database
        removeRow(announcement_id)
        
        # Broadcast the deletion to all clients
        emit('announcement_deleted', {
            'id': announcement_id
        }, broadcast=True)
        
        # print(f'Deleted announcement {announcement_id}')
        
    except Exception as e:
        # print(f'Error deleting announcement: {e}')
        emit('error', {'message': 'Failed to delete announcement'})

@socketio.on('connect')
def handle_connect():
    print('Client connected')

@socketio.on('disconnect')
def handle_disconnect():
    print('Client disconnected')

if __name__ == "__main__":
    socketio.run(
        app,
        host='0.0.0.0',
        port=5000,
        debug=True
    )