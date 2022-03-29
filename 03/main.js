function calculateFactorial(number){
rawNumber=1
for (let i= 1; i <= number; i++) {
    rawNumber*=i 
}
return rawNumber
}

function reverseString(string){
    let arrayChar = string.split("")
    let reverse= arrayChar.reverse()
    let newString = reverse.join("")
   return newString
} 
function translate(code){
switch (code) {
    case "VN":
        return "Xin Chào"
        break;
    case "EN":
        return "Hello"
        break;
    case "SG":
        return"Hello"
        break;
    case "JP":
        return"こんにちは"
        break;
}
}
function subString(str){
    s=str.substring(0,10)
    returns+"..."
}
console.log(calculateFactorial(5))
console.log(reverseString("hello"))
console.log(translate("JP"))
console.log(subString("xinchaocacbandenvoiTechmaster") )




