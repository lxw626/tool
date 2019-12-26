let routes = [
    //每个路由对应一个组件
    {path: '/Emp', component: Emp},
    {path: '/Dept', component: Dept},
    {path:'/', redirect:'/Emp'
    }
]
let router = new VueRouter({
    routes
})
new Vue({
    el: '#app',
    router
})