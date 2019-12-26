axios.get("http://localhost:8888/getVueTemplate").then(function(res){
    let allinfo = res.data.allinfo
    let body = document.getElementsByTagName("body")[0].innerHTML += allinfo;
    // document.write(allinfo)

})

