<template>
    <div>
        <el-container style=" border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)" direction="vertical">
                <el-menu router>
                    <el-menu-item index="/Emp">员工管理</el-menu-item>
                    <el-menu-item index="/Dept">部门管理</el-menu-item>
                </el-menu>
            </el-aside>
            <el-main style="padding: 0px">
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script>

    export default {}
</script>

<style>

</style>