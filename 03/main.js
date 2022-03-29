function calculateFactorial(number){
rawNumber=1
for (let i= 1; i <= number; i++) {
    rawNumber*=i 
}
console.log(rawNumber)
}

function reverseString(string){
    let arrayChar = string.split("")
    let reverse= arrayChar.reverse()
    let newString = reverse.join("")
    console.log(newString)
} 
function translate(code){
switch (code) {
    case "VN":
        console.log("Xin Chào")
        break;
    case "EN":
        console.log("Hello")
        break;
    case "SG":
        console.log("Hello")
        break;
    case "JP":
        console.log("こんにちは")
        break;
}
}
function subString(str){
    s=str.substring(0,10)
    console.log(s+"...")
}
calculateFactorial(5)
reverseString("123")
translate("JP")
subString("xinchaocacbandenvoiTechmaster") 
