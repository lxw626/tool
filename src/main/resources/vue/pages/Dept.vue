<template>
    <div>
        <el-table :data="depts">
            <el-table-column prop="deptno" label="部门编号" width="100" align="center"></el-table-column>
            <el-table-column prop="dname" label="部门名称" ></el-table-column>
            <el-table-column prop="loc" label="所在地"></el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange" @current-change="handleCurrentChange"
            layout="total, sizes, prev, pager, next, jumper" :current-page="currentPage"
            :page-sizes="[3,5, 10, 15]" :page-size="3" :total="total">
        </el-pagination>
    </div>
</template>

<script>

    export default {
        data: function () {
            return {
                depts: [],
                dept: { // 新增(新增前需要清空emp),编辑(需要将当前行赋值给emp),查询(与查询条件双向绑定)共用
                    deptno: undefined,
                    dname: '',
                    loc: ''
                },
                total: 0,
                currentPage: 1,
                pageSize: 3,
                sortInfo: 'deptno asc'
            }
        },
        methods: {
            // pageSize变动,触发重新从后台加载数据
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.getDepts()
            },
            // currentPage变动,触发从后台加载数据
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage
                this.getDepts()
            },
            async getDepts() {
                let url = 'getDeptsBypage'
                let {dept, currentPage, pageSize, sortInfo} = this
                // 组装分页与排序
                dept.currentPage = currentPage
                dept.pageSize = pageSize
                dept.sortInfo = sortInfo
                let deptPageInfo = await ajax(url, dept, 'POST')
                this.depts = deptPageInfo.list
                this.total = deptPageInfo.total
            },
        },
        mounted() {
            this.getDepts()
        }
    }
</script>

<style>

</style>