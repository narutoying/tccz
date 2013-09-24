function buildButton(itemId, text, aLink, confirmCfg){
    var confirmStr = "";
    if (confirmCfg != null) {
        confirmStr = "if(confirm(\"" + confirmCfg.text + "\"))";
    }
    return "<button onclick='" + confirmStr + "window.location.href=\"" + getContextPath() + aLink + "?itemId=" + itemId + "\"'>" + text + "</button>"
}
