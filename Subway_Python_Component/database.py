import os
from supabase import create_client, Client
from dotenv import load_dotenv
import json
from datetime import datetime 
import pytz
load_dotenv()
tz = pytz.timezone('Asia/Hong_Kong')

url = os.environ.get("url")
key = os.environ.get("key")
supabase = create_client(url,key)
def getData():
  response = (
      supabase.table("announcements")
      .select("*")
      .execute()
  )
  response = response.model_dump_json()
  response = json.loads(response)
  return response['data']

def getDataByID(id):
  response = (
      supabase.table("announcements")
      .select("*")
      .execute()
  )
  response = response.model_dump_json()
  response = json.loads(response)
  data = response['data']
  for i in data:
    if i['id'] == id:
      return i
  return None

def addRow(author,message,date):
  supabase.table("announcements").insert({"author":author,"message":message,"timestamp":date}).execute()
  id = getData()[-1]['id']
  return id

def editRow(message,author,id):
  og = getDataByID(id)
  name = og['author']
  date = datetime.now(tz).strftime("%Y-%m-%d %H:%M:%S")
  if author != name:
    name += ' & ' + author
  supabase.table("announcements").update({"author": name}).eq("id", id).execute()
  supabase.table("announcements").update({"message": message}).eq("id", id).execute()
  supabase.table("announcements").update({"timestamp": date}).eq("id", id).execute()

def removeRow(id):
  try:
    response = (
    supabase.table("announcements").delete().eq("id", id).execute()
)
  except:
    return