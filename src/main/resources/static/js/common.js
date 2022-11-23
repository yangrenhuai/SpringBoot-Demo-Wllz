//var web_prefix = '/backend'

// 定义全局转换日期到String
Vue.prototype.parsetDatetoStr=function(date){
    let d = new Date(date);
    let year=d.getFullYear();
    let month=d.getMonth()+1;
    let day=d.getDate();
    if(month<10) month='0'+month;
    if(day<10) day='0'+day;
    return year+'-'+month+'-'+day;
}

Vue.prototype.parsetDateTimetoStr=function(date){
    let d = new Date(date);
    let year=d.getFullYear();
    let month=d.getMonth()+1;
    let day=d.getDate();
    let hour=d.getHours();
    let minute=d.getMinutes();
    let seconds=d.getSeconds();

    if(month<10) month='0'+month;
    if(day<10) day='0'+day;
    if(hour<10) hour='0'+hour;
    if(minute<10) minute='0'+minute;
    if(seconds<10) seconds='0'+seconds;
    return year+'-'+month+'-'+day+" "+hour+":"+minute+":"+seconds;
}

//自定义全局过滤器，将时间戳显示成年月日
Vue.filter('date',data=>{
    let d = new Date(data);
    if(d.getFullYear()===1970) return "-";
    var year=d.getFullYear();
    var month;
    if(d.getMonth()+1<10)
        month='0'+(d.getMonth()+1)
    else
        month=d.getMonth()+1;
    var day;
    if(d.getDate()<10)
        day='0'+(d.getDate())
    else
        day=d.getDate();
    return year+'-'+month+'-'+day;
});
//自定义全局过滤器，将时间戳显示成年月日
Vue.filter('datetime',date=>{
    let d = new Date(date);
    let year=d.getFullYear();
    let month=d.getMonth()+1;
    let day=d.getDate();
    let hour=d.getHours();
    let minute=d.getMinutes();
    let seconds=d.getSeconds();

    if(month<10) month='0'+month;
    if(day<10) day='0'+day;
    if(hour<10) hour='0'+hour;
    if(minute<10) minute='0'+minute;
    if(seconds<10) seconds='0'+seconds;
    return year+'-'+month+'-'+day+" "+hour+":"+minute+":"+seconds;
});