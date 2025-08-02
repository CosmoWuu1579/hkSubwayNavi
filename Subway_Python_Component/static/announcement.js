let socketIOs = io();

document.addEventListener('DOMContentLoaded', function() {
    loadExistingAnnouncements();
});

// Socket event listeners
socketIOs.on('announcement', (data) => {
    addMessage(
        data.msg,
        data.author,
        data.time,
        data.id
    );
});

socketIOs.on('announcement_updated', (data) => {
    updateMessage(data.id, data.msg, data.author, data.time);
});

socketIOs.on('announcement_deleted', (data) => {
    removeMessage(data.id);
});

socketIOs.on('error', (data) => {
    alert('Error: ' + data.message);
});

// Load existing announcements from database
async function loadExistingAnnouncements() {
    try {
        const response = await fetch('/api/announcements');
        const data = await response.json();
        
        if (data.success) {
            const container = document.getElementById('announcementsList');
            container.innerHTML = ''; // Clear loading message
            
            if (data.announcements && data.announcements.length > 0) {
                // Sort announcements by timestamp (newest first)
                
                data.announcements.sort((a, b) => -new Date(b.timestamp) + new Date(a.timestamp));
                // console.log(data.announcements);
                data.announcements.forEach(announcement => {
                    addMessage(
                        announcement.message,
                        announcement.author,
                        announcement.timestamp,
                        announcement.id,
                        false // Don't clear loading message again
                    );
                });
            } else {
                container.innerHTML = '<div class="loading"><p>No announcements yet. Be the first to post!</p></div>';
            }
        } else {
            console.error('Failed to load announcements:', data.error);
            const container = document.getElementById('announcementsList');
            container.innerHTML = '<div class="loading"><p>Error loading announcements. Please refresh the page.</p></div>';
        }
    } catch (error) {
        console.error('Error fetching announcements:', error);
        const container = document.getElementById('announcementsList');
        container.innerHTML = '<div class="loading"><p>Error loading announcements. Please refresh the page.</p></div>';
    }
}

function addMessage(message, author, date, id, clearLoading = true) {
    const container = document.getElementById('announcementsList');
    
    if (clearLoading && container.innerHTML.includes('Loading announcements...')) {
        container.innerHTML = '';
    }
    
    // Check if message already exists (to avoid duplicates)
    if (document.querySelector(`[data-announcement-id="${id}"]`)) {
        return;
    }
    const formatted = formatDateTimeWithSeconds(date);
    // console.log(formatted);
    // console.log(new Date(date).toLocaleString());
    let html = `
        <div class="announcement-card" data-announcement-id="${id}">
            <div class="announcement-header">
                <div class="announcement-meta">
                    <span>👤 ${escapeHtml(author)}</span>
                    <span>🕒 ${formatted}</span>
                </div>
                <div class="announcement-actions">
                    <button class="btn btn-small btn-edit" onclick="openEditModal(${id}, '${escapeHtml(author)}', '${escapeHtml(message)}')">
                        ✏️ Edit
                    </button>
                    <button class="btn btn-small btn-delete" onclick="deleteAnnouncement(${id})">
                        🗑️ Delete
                    </button>
                </div>
            </div>
            <div class="announcement-message">
                ${escapeHtml(message).replace(/\n/g, '<br>')}
            </div>
        </div>
    `;
    
    container.insertAdjacentHTML('afterbegin', html);
}

function updateMessage(id, message, author, date) {
    const messageElement = document.querySelector(`[data-announcement-id="${id}"]`);
    const formatted = formatDateTimeWithSeconds(date);
    // console.log(formatted);
    if (messageElement) {
        messageElement.innerHTML = `
            <div class="announcement-header">
                <div class="announcement-meta">
                    <span>👤 ${escapeHtml(author)}</span>
                    <span>🕒 ${formatted}</span>
                    <span style="color: #888; font-style: italic;">✏️ (edited)</span>
                </div>
                <div class="announcement-actions">
                    <button class="btn btn-small btn-edit" onclick="openEditModal(${id}, '${escapeHtml(author)}', '${escapeHtml(message)}')">
                        ✏️ Edit
                    </button>
                    <button class="btn btn-small btn-delete" onclick="deleteAnnouncement(${id})">
                        🗑️ Delete
                    </button>
                </div>
            </div>
            <div class="announcement-message">
                ${escapeHtml(message).replace(/\n/g, '<br>')}
            </div>
        `;
    }
}

function removeMessage(id) {
    const messageElement = document.querySelector(`[data-announcement-id="${id}"]`);
    if (messageElement) {
        messageElement.remove();
        
        // Check if there are no more announcements
        const container = document.getElementById('announcementsList');
        if (!container.querySelector('.announcement-card')) {
            container.innerHTML = '<div class="loading"><p>No announcements yet. Be the first to post!</p></div>';
        }
    }
}

function sendMessage(event) {
    if (event) {
        event.preventDefault();
    }
    
    const messageElement = document.getElementById("message");
    const authorElement = document.getElementById("author_name");
    
    console.log("Sending message...");
    
    if (!messageElement || !authorElement) {
        console.error("Form elements not found");
        return false;
    }
    
    const message = messageElement.value.trim();
    const author = authorElement.value.trim();
    
    if (!message || !author) {
        alert("Please fill in both name and message fields");
        return false;
    }
    if(author.length > 100){
        alert("Please shorten in the name field");
        return false;
    }
    
    console.log("Emitting message:", { msg: message, author: author });
    
    socketIOs.emit('message', {
        'msg': message,
        'author': author
    });
    
    // Clear the form
    messageElement.value = '';
    authorElement.value = '';
    
    return false; 
}

function openEditModal(id, author, message) {
    const modal = document.getElementById('editModal');
    const editId = document.getElementById('edit_announcement_id');
    const editAuthor = document.getElementById('edit_author_name');
    const editMessage = document.getElementById('edit_message');
    
    if (modal && editId && editAuthor && editMessage) {
        editId.value = id;
        editAuthor.value = author;
        editMessage.value = message;
        modal.style.display = 'block';
    }
}

function closeEditModal() {
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

function submitEdit(event) {
    if (event) {
        event.preventDefault();
    }
    
    const editId = document.getElementById('edit_announcement_id').value;
    const editAuthor = document.getElementById('edit_author_name').value.trim();
    const editMessage = document.getElementById('edit_message').value.trim();
    
    if (!editAuthor || !editMessage) {
        alert("Please fill in both name and message fields");
        return false;
    }
    
    socketIOs.emit('edit_announcement', {
        'id': parseInt(editId),
        'author': editAuthor,
        'msg': editMessage
    });
    
    closeEditModal();
    return false;
}

function deleteAnnouncement(id) {
    if (confirm("Are you sure you want to delete this announcement?")) {
        socketIOs.emit('delete_announcement', {
            'id': id
        });
    }
}

// Utility function to escape HTML to prevent XSS
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function formatDateTimeWithSeconds(isoString) {
    const [datePart, timePartWithZone] = isoString.split("T");
    const [year, month, day] = datePart.split("-");

    const [timePart] = timePartWithZone.split("+"); 
    let [hour, minute, second] = timePart.split(":");
    let secondsFloat = parseFloat(second);

    let roundedSeconds = Math.round(secondsFloat);

    // Handle rollover if rounding causes 60 seconds
    if (roundedSeconds === 60) {
        roundedSeconds = 0;
        minute = String(parseInt(minute) + 1).padStart(2, '0');

        // Handle minute rollover
        if (parseInt(minute) === 60) {
            minute = '00';
            hour = String(parseInt(hour) + 1).padStart(2, '0');
        }
    }

    hour = parseInt(hour);
    const ampm = hour >= 12 ? "PM" : "AM";
    const hour12 = hour % 12 || 12;

    return `${parseInt(month)}/${parseInt(day)}/${year} ${hour12}:${minute}:${String(roundedSeconds).padStart(2, '0')} ${ampm}`;
}

// Close modal when clicking outside of it
window.onclick = function(event) {
    const modal = document.getElementById('editModal');
    if (event.target == modal) {
        closeEditModal();
    }
}