/**
 * 生成四位随机数
 * @param img
 */
function changeVerifyCode(img) {
    img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}

function getQueryString(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //没有*，会只有个位数字匹配成功；加上*，就可以匹配任意长度
    var r = window.location.search.substr(1).match(reg);
    if(r != null){
        return decodeURIComponent(r[2]);
    }
    return '';
}