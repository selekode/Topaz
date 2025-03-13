function openModal(id) {
    console.log("Opening modal for entry with ID: " + id);  // Debug log
    document.getElementById('journalId').value = id;
    document.getElementById('confirmModal').classList.remove('hidden');
    console.log("Modal should now be visible");
}

function closeModal() {
    console.log("Closing modal");  // Debug log
    document.getElementById('confirmModal').classList.add('hidden');
}