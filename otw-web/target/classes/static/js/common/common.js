var TOKEN_KEY = 'x-access-token'

window.createTableStrWithoutIndex = function(paramArr,datas,hasBtn,btnStr){
    var str = "";
    if(datas.length>0){
        if(!hasBtn){
            for(var i = 0; i< datas.length;i++) {
                str+="<tr>";
                var data = datas[i];
                for (var j = 0; j < paramArr.length; j++) {
                    var param = paramArr[j];
                    str +="<td>" + data[param] + "</td>";
                }
                str+="</tr>";
            }
        }else{
            for(var i = 0; i< datas.length;i++){
                str+="<tr>";
                var data = datas[i];
                for(var j=0;j<paramArr.length;j++){
                    var param = paramArr[j];
                    if(param === "btn"){
                        str += "<td>" + btnStr + "</td>";
                    }else {
                        str +="<td>" + data[param] + "</td>";
                    }
                }
                str+="</tr>";
            }
        }
    } else{
        str+="<tr><td colspan='" + paramArr.length + "'>没有更多数据</td></tr>";
    }


    str += "</tr>";
    return str;
}

window.createTableStrWithIndex = function(paramArr,datas,hasBtn,btnStr){
    var str ="";
    if(datas.length>0){
        if(!hasBtn){
            for(var i = 0; i< datas.length;i++) {
                str+="<tr>";
                var data = datas[i];
                for (var j = 0; j < paramArr.length; j++) {
                    var param = paramArr[j];
                    if(param === "index"){
                        str +="<td>" + (i+1) + "</td>"
                    }else{
                        str +="<td>" + data[param] + "</td>";
                    }
                }
                str+="</tr>";
            }
        }else{
            for(var i = 0; i< datas.length;i++){
                str+="<tr>";
                for(var j=0;j<paramArr.length;j++){
                    var param = paramArr[j];
                    var data = datas[i];
                    if(param === "btn"){
                        str += "<td>" + btnStr + "</td>";
                    }else if(param === "index"){
                        str +="<td>" + (i+1) + "</td>"
                    }else{
                        str +="<td>" + data[param] + "</td>";
                    }
                }
                str+="</tr>";
            }
        }
    }else{
        str+="<tr><td colspan='" + paramArr.length + "'>没有更多数据</td></tr>";
    }
    return str;
}

window.createTableHeader = function(arr){
    var str = "<tr>";
    for(var i = 0;i<arr.length;i++){
        str += "<th style='width:" + (arr[i].width?arr[i].width:"auto") + "'>" + arr[i].text + "</th>";
        console.log(str);
    }
    str += "</tr>";
    return str;
}

function syncPost(url,data,callback,errorCallback){
    $.ajax({
        url:url,
        type:"post",
        data:JSON.stringify(data),
        contentType: 'application/json; charset=UTF-8',
        dataType:"json",
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(TOKEN_KEY, getToken());
        },
        success: callback,
        error:errorCallback
    });
}

function syncGet(url,data,callback,errorCallback){
    $.ajax({
        url:url,
        type:"get",
        data:data,
        dataType:"json",
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(TOKEN_KEY, getToken());
        },
        success: callback,
        error:errorCallback
    });
}
function syncDelete(url,data,callback,errorCallback){
    $.ajax({
        url:url,
        type:"delete",
        data:data,
        dataType:"json",
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(TOKEN_KEY, getToken());
        },
        success: callback,
        error:errorCallback
    });
}
function syncPostText(url,data,callback,errorCallback){
    $.ajax({
        url:url,
        type:"post",
        data:data,
        dataType:"json",
        contentType: 'text; charset=UTF-8',
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(TOKEN_KEY, getToken());
        },
        success: callback,
        error:errorCallback
    });
}
function setToken(token){
    $.cookie(TOKEN_KEY,token,{expire:1})
}

function getToken(){
    // var token = $.cookie(TOKEN_KEY)
    var token = "123"
    if (token) {
        return token
    }
    return false
}
/**
 * 全局处理请求相应
 */
function setAjaxOptions(){
    var code = {
        401: function() {
            window.location.replace("/login") //权限检验状态
        },
        404: function() {
            window.location.replace("/notFound") //not found 页面
        },
        500: function() {
            alert('服务器有误。500');
        }
    }
    $.ajaxSetup({
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        beforeSend: function(xhr) {
            xhr.setRequestHeader(TOKEN_KEY, "testAE");
        },
        complete:function(XMLHttpRequest){
            var data =  JSON.parse(XMLHttpRequest.responseText);
             code[data.code]()
        }
    });
}


function verifyUsername(str){
    var user = /^[a-zA-Z0-9_\.@]{6,20}$/;
    if (user.test(str))
    {
        return true;
    }
    else {
        return false;
    }
}

function verifyPassword(str){
    var pwd = /^([a-z0-9\.\@\!\#\$\%\^\&\*\(\)]){6,20}$/;
    if (pwd.test(str))
    {
        return true;
    }
    else {
        return false;
    }
}

function verifyPhone(str){
    var phone= /^[1]\d{10}$/;
    if(phone.test(str)){
        return true;
    }
    else {
        return false;
    }
}

function verifyCode(str){
    var code = /^([\dA-HJ-NP-RT-UW-Y])([\dA-HJ-NP-RT-UW-Y])(\d{6})([\dA-HJ-NP-RT-UW-Y]{9})([\dA-HJ-NP-RT-UW-Y])(-\d{2})?$/;
    if(code.test(str)){
        return true;
    }
    else {
        return false;
    }
}

window.createMesDialog = function(header,content,foot,callback){
    var mesDialog = "";
    $(".mes-dialog").remove();
    mesDialog +='<div class="mes-dialog">' +
        '<div class="content">' +
        '<div class="content-header">' + header + '</div>' +
        '<div class="content-body">' +
        '<div class="register-content">' +content +
        '</div>' +
        '<div class="content-foot">' + foot + '</div>' +
        '</div>' +
        '</div>';
    $($("body")[0]).append(mesDialog);
    callback();
}

window.createLogin = function(){
    var mesDialog = "";
    $(".mes-dialog").remove();
    mesDialog +='<div class="login-dialog">' +
        '</div>';
    $($("body")[0]).append(mesDialog);
    callback();
}

window.createChooseDialog = function(header,content,foot,firstCallback,secondCallback){
    var mesDialog = "";
    $(".mes-dialog").remove();
    mesDialog +='<div class="mes-dialog">' +
        '<div class="content">' +
        '<div class="content-header">' + header + '</div>' +
        '<div class="content-body">' +
        '<div class="register-content">' +content +
        '</div>' +
        '<div class="content-foot">' + foot + '</div>' +
        '</div>' +
        '</div>';
    $($("body")[0]).append(mesDialog);
    firstCallback();
    secondCallback();
}
$(document).ready(function () {
    // setAjaxOptions()
});
