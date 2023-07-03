
function enc(pwd){
    if(!pwd || " "==pwd){
        return "";
    }
    let enc1 = random(5)+pwd+random(5);
	let enc2 = window.btoa(enc1);
	let enc3 = enc2.split("").reverse().join("");
	let enc4 = (new Date().getTime()/1000 >> 0)+"&&"+enc3;
	let enc5 = window.btoa(enc4);
	let enc6 = strToHex(enc5);
    return enc6;
}

function strToHex(str) {
　　if(str === ""){
        return "";
   }
　　let hex_code = [];
　　for(let i = 0; i < str.length; i++) {
　　　　hex_code.push((str.charCodeAt(i)).toString(16));
　　}
　　return hex_code.join("");
}

var _chars = 'abcdefghijklmnopqrstuvwxyz12345678ABCDEFGHJKLMNOPQRSTUVWXYZ';
function random(len) {
　　len = len || 32;
　　let char_length = _chars.length;
　　let c = '';
　　for (let i = 0; i < len; i++) {
　　　　c += _chars.charAt(Math.floor(Math.random() * char_length));
　　}
　　return c;
}

export { enc }