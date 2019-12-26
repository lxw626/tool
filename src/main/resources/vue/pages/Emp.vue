<template>
    <div>
        <div style="margin-top: 25px;margin-bottom: 0px">
            <el-form inline :model="emp" size="mini">
                <el-form-item label="姓名:" placeholder="">
                    <el-input v-model="emp.ename"></el-input>
                </el-form-item>
                <el-form-item label="岗位:" placeholder="">
                    <el-input v-model="emp.job"></el-input>
                </el-form-item>
                <el-form-item label="入职日期:" placeholder="">
                    <el-date-picker v-model="emp.hiredate"></el-date-picker>
                </el-form-item>
                <el-form-item label="部门:" placeholder="">
                    <el-input v-model="emp.deptno"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button icon="el-icon-search" type="primary" @click="getEmps"></el-button>
                </el-form-item>
                <el-form-item>
                    <el-checkbox border size="mini" label="远程排序" v-model="remoteSort"></el-checkbox>
                </el-form-item>
            </el-form>
        </div>
        <el-table :data="emps" stripe border show-header highlight-current-row max-height="500"
                  tooltip-effect="dark"
                  @selection-change="handleSelectionChange" @filter-change="filterChange"
                  @sort-change="sortChange" @row-dblclick="rowDblclick">
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="empno" label="编号" width="100" :sortable="remoteSort?'custom':true" align="center"></el-table-column>
            <el-table-column prop="ename" label="姓名" ></el-table-column>
            <el-table-column prop="job" label="岗位" column-key="job"
                             :filters="jobs"
                             :filter-method="filterHandler"></el-table-column>
            <el-table-column prop="mgr" label="领导编号" width="100"></el-table-column>
            <el-table-column prop="hiredate" label="入职日期" width="180"></el-table-column>
            <el-table-column prop="sal" label="薪资" :sortable="remoteSort?'custom':true"></el-table-column>
            <el-table-column prop="comm" label="业绩" :sortable="remoteSort?'custom':true"></el-table-column>
            <el-table-column prop="deptno" label="部门编号" width="100" column-key="deptno"
                             :filters="deptnos"
                             :filter-method="filterHandler"></el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <el-button size="mini" @click="openEmpForm(2,scope.$index,scope.row)"
                               type="primary" icon="el-icon-edit">编辑</el-button>
                    <el-button size="mini" @click="deleteEmpByEmpno(scope.row)"
                               type="danger" icon="el-icon-delete">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-row>
            <el-col :span="18">
                <el-pagination
                    @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    layout="total, sizes, prev, pager, next, jumper" :current-page="currentPage"
                    :page-sizes="[3,5, 10, 15]" :page-size="3" :total="total">
                </el-pagination>
            </el-col>
            <el-col :span="2" class="rightAlign">
                <el-button size="medium" icon="el-icon-plus" type="primary"
                           @click="openEmpForm(1)">新增员工</el-button>
            </el-col>
            <el-col :span="2" class="rightAlign">
                <el-button size="medium" type="primary" @click="showNote=true">备注信息</el-button>
            </el-col>
            <el-col :span="2" class="rightAlign">
                <el-button size="medium" type="primary">
                    <a href="./app.html" style="text-decoration: none;color: white">移动端打开</a>
                </el-button>
            </el-col>
        </el-row>
        <!-- .sync 不写点击右上角的叉号不能关闭,点击遮罩也不能关闭-->
        <el-dialog title="员工信息" :visible.sync="showForm">
            <el-form :model="emp" size="mini" :rules="rules">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="员工编号:" :label-width="formLabelWidth">
                            <el-input v-model="emp.empno" readonly :disabled="saveType==1?true:false"
                                      style="width: 80%;"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="员工姓名:" :label-width="formLabelWidth" prop="ename">
                            <el-input v-model="emp.ename" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="岗位:" :label-width="formLabelWidth">
                            <el-input v-model="emp.job" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="领导编号:" :label-width="formLabelWidth"  prop="mgr">
                            <el-input v-model.number="emp.mgr" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="入职日期:" :label-width="formLabelWidth">
                            <el-date-picker v-model="emp.hiredate" type="date" clearable placeholder="选择日期"
                                            format="yyyy-MM-dd"  value-format="yyyy-MM-dd" style="width: 80%"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="薪资:" :label-width="formLabelWidth" prop="sal">
                            <el-input v-model.number="emp.sal" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="业绩:" :label-width="formLabelWidth" prop="comm">
                            <el-input v-model.number="emp.comm" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="部门编号:" :label-width="formLabelWidth" prop="deptno">
                            <el-input v-model.number="emp.deptno" style="width: 80%"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="saveEmp">确 定</el-button>
                <el-button @click="showForm = false">取 消</el-button>
            </div>
        </el-dialog>
        <el-dialog title="说明" :visible.sync="showNote" width="30%">
            <p>
                前段过滤:多字段过滤,多个字段之间用and连接
            </p>
            <p>
                后端过滤:多字段过滤,多个字段之间用and连接
            </p>
            <p>
                前段排序:单字段排序,
            </p>
            <p>
                后端排序:单字段排序(可扩展为多字段排序)
            </p>
            <p>
                前段用到了Vue,VueRouter,ElementUI(el-menu	el-menu-item	el-container	el-aside	el-main
                el-row	el-col el-table el-table-column	el-pagination	el-form	el-form-item	el-button
                el-input	el-input-number	el-date-picker	el-dialog	el-checkbox) axios
            </p>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showNote=false">关 闭</el-button>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    export default {
        mounted() {
            this.getEmps()
            this.getStatistics()
        },
        data: function () {
            return {
                showForm: false,
                remoteSort: false,
                showNote: false,
                saveType: 0,
                formLabelWidth: '100px',
                emps: [],
                jobs: [],
                deptnos: [],
                total: 0,
                emp: { // 新增(新增前需要清空emp),编辑(需要将当前行赋值给emp),查询(与查询条件双向绑定)共用
                    empno: undefined,
                    ename: '',
                    job: '',
                    mgr: undefined,
                    hiredate: undefined,
                    sal: undefined,
                    comm: undefined,
                    deptno: undefined,
                },
                sorts: [],
                sortInfo: '', // sortInfo,currentPage,pageSize放在emp里面容易在赋值的过程中覆盖掉导致后端空指针,所以在发送请求前组装进去
                currentPage: 1,
                pageSize: 3,
                rules: {
                    ename: [
                        {required: true, message: '请输入员工姓名', trigger: 'blur'},
                        {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'}
                    ],
                    mgr: [
                        {type: 'number', message: 'mgr必须为数字', trigger: 'change'}
                    ],
                    sal: [
                        {type: 'number', message: 'sal必须为数字', trigger: 'change'}
                    ],
                    comm: [
                        {type: 'number', message: 'comm必须为数字', trigger: 'change'}
                    ],
                    deptno: [
                        {type: 'number', message: 'deptno必须为数字', trigger: 'change'}
                    ],

                }
            }
        },
        methods: {
            // pageSize变动,触发重新从后台加载数据
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.getEmps()
            },
            // currentPage变动,触发从后台加载数据
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage
                this.getEmps()
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 前端过滤条件改变后可以考虑在这里重新加载后台的数据
            filterChange(filter) {
            },
            // 远程排序
            sortChange(column) {
                if (this.remoteSort) {
                    let order = column.order == 'ascending' ? 'ASC' : 'DESC'
                    let prop = column.prop
                    this.sorts.push({prop, order})
                    let sorts = this.sorts;
                    let result = []
                    let tmp = []
                    for (let i = sorts.length - 1; i >= 0; i--) {
                        if (tmp.indexOf(sorts[i].prop) == -1) {
                            tmp.push(sorts[i].prop)
                            result.push(sorts[i])
                        }
                    }
                    tmp = []
                    this.sorts = result
                    let sortInfo = ''
                    for (let i = result.length - 1; i >= 0; i--) {
                        sortInfo += result[i].prop + ' ' + result[i].order + ','
                    }
                    if (sortInfo.endsWith(',')) {
                        sortInfo = sortInfo.substr(0, sortInfo.length - 1)
                    }
                    this.sortInfo = sortInfo;
                    this.getEmps()
                }
            },
            rowDblclick(row, column, event) {
                this.openEmpForm(2, null, row)
            },
            // 打开新增,编辑对话框
            openEmpForm(saveType, index, row,) {
                this.showForm = true
                this.saveType = saveType
                if (saveType == 1) {
                    this.emptyEmp()
                } else if (saveType == 2) {
                    this.emp = row
                }
            },
            // 清空emp
            emptyEmp() {
                this.emp.empno = undefined
                this.emp.ename = ''
                this.emp.job = ''
                this.emp.mgr = undefined
                this.emp.hiredate = undefined
                this.emp.sal = undefined
                this.emp.comm = undefined
                this.emp.deptno = undefined
            },
            // 远程加载数据
            async getEmps() {
                let url = 'getEmpsBypage'
                let {emp, currentPage, pageSize, sortInfo} = this
                // 组装分页与排序
                emp.currentPage = currentPage
                emp.pageSize = pageSize
                emp.sortInfo = sortInfo
                let empPageInfo = await ajax(url, emp, 'POST')
                this.emps = empPageInfo.list
                this.total = empPageInfo.total
            },
            // 远程加载jobs和deptnos
            async getStatistics() {
                let url = 'getStatistics'
                let statistics = await ajax(url)
                let jobs = statistics.jobs.map(e => ({
                    text: e,
                    value: e
                }))
                let deptnos = statistics.deptnos.map(e => ({
                    text: e,
                    value: e
                }))
                this.jobs = jobs
                this.deptnos = deptnos
            },
            // 新增/修改emp
            async saveEmp() {
                this.showForm = false
                let url = 'emp'
                let type = ''
                if (this.saveType == 1) {
                    type = 'POST'
                } else if (this.saveType == 2) {
                    type = 'PUT'
                }
                let result = await ajax(url, this.emp, type)
                this.emptyEmp()
                this.getEmps()
            },
            // 删除
            deleteEmpByEmpno(row) {
                let empno = row.empno
                let url = `emp/${empno}`
                let me = this
                this.$confirm(`是否要删除${row.ename}`, '提示', {type: 'warning'}).then(
                    async function () {
                        let result = await ajax(url, empno, 'DELETE')
                        me.getEmps();
                    }
                )

            },
            /**
             * 该方法会循环调用,即每一行调用一次,返回true则显示改行,返回false则不显示改行
             * @param value
             * @param row
             * @param column
             * @returns {boolean}
             */
            filterHandler(value, row, column) {
                // 触发该方法的列名
                const columnName = column['property'];
                // 该列当前行的该列的值
                let columnValue = row[columnName]
                return columnValue == value;
            }
        },
    }
</script>

<style>

</style>