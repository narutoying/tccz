function buildButton(itemId, text, aLink){
    return "<button onclick='window.location.href=\"" + getContextPath() + aLink + "?id=" + itemId + "\"'>" + text + "</button>"
}