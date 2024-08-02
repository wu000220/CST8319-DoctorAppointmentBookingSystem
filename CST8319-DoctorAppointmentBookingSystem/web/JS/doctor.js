/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', () => {
    const noteButtons = document.querySelectorAll('.note-button');
    const noteModal = document.getElementById('noteModal');
    const closeModal = document.querySelector('.modal .close');
    const noteForm = document.getElementById('noteForm');
    const appointmentIDField = document.getElementById('appointmentID');

    noteButtons.forEach(button => {
        button.addEventListener('click', () => {
            const appointmentID = button.getAttribute('data-id');
            appointmentIDField.value = appointmentID;
            noteModal.style.display = 'block';
        });
    });

    closeModal.addEventListener('click', () => {
        noteModal.style.display = 'none';
    });

    window.onclick = function(event) {
        if (event.target === noteModal) {
            noteModal.style.display = 'none';
        }
    };
});


