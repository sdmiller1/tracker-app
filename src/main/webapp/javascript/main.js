const displayMessage = (messageText, style) => {
    // close any previous message
    closeMessage();

    let messageElement = document.querySelector(".message");
    let message = document.querySelector("#messageText");

    message.textContent = messageText;

    messageElement.className = "message " + style;

    messageElement.style.display = "block";
}

const closeMessage = () => {
    let message = document.querySelector(".message");
    message.style.display = "none";
}