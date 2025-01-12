<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <!-- 头部 -->
            <el-header style="font-size:40px;background-color: rgb(238, 241, 246)">学生信息管理系统</el-header>

            <!-- 边框 -->
            <el-container>
                <el-aside width="200px">
                    <el-menu :default-openeds="['1', '3']">
                        <el-menu-item index="2">
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/course">课程信息</router-link></template>
                        </el-menu-item>
                        <el-menu-item index="3">
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/student">学生信息</router-link></template>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/teacher">教师信息</router-link></template>
                        </el-menu-item>
                        <el-menu-item index="5">
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/dormitory">宿舍信息</router-link></template>
                        </el-menu-item>
                    </el-menu>
                </el-aside>

                <!-- 主页 -->
                <el-main>
                    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
                        <el-form-item label="教师号：">
                            <el-input v-model="searchForm.tno" placeholder="教师号"></el-input>
                        </el-form-item>
                        <el-form-item label="教师名：">
                            <el-input v-model="searchForm.tn" placeholder="教师名"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="handleSearch">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="dialogFormVisible_add = true"
                                icon="el-icon-edit"></el-button>
                        </el-form-item>
                    </el-form>
                    <el-table :data="tableData">
                        <el-table-column prop="tno" label="教师号" width="250">
                        </el-table-column>
                        <el-table-column prop="tn" label="教师名" width="250">
                        </el-table-column>
                        <el-table-column prop="gender" label="性别" width="250">
                        </el-table-column>
                        <el-table-column prop="office" label="办公室" width="250">
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button @click=" handleEdit(scope.$index) " type="primary" size="small"
                                    style="margin-right: 20px;">修改</el-button>
                                <!-- <el-button @click="handleDelete(scope.$index)" type="danger" size="small">删除</el-button> -->
                                <template>
                                    <el-popconfirm title="确定删除吗？" @confirm=" handleDelete(scope.$index) ">
                                        <el-button slot="reference">删除</el-button>
                                    </el-popconfirm>
                                </template>
                            </template>
                        </el-table-column>
                    </el-table>

                    <!-- 添加信息框 -->
                    <el-dialog title="添加教师信息" :visible.sync="dialogFormVisible_add">
                        <el-form :ref="form" model="form">
                            <el-form-item label="教师号">
                                <el-input v-model="form.tno"></el-input>
                            </el-form-item>
                            <el-form-item label="教师名">
                                <el-input v-model="form.tn"></el-input>
                            </el-form-item>
                            <el-form-item label="性别">
                                <el-input v-model="form.gender"></el-input>
                            </el-form-item>
                            <el-form-item label="办公室">
                                <el-input v-model="form.office"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click=" dialogFormVisible_add = false; clear_form();">取 消</el-button>
                            <el-button type="primary" @click=" handleAdd(); clear_form();">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!-- 修改信息框 -->
                    <el-dialog title="修改教师信息" :visible.sync="dialogFormVisible_update">
                        <el-form :ref="form" model="form">
                            <el-form-item label="教师号">
                                <el-input v-model="form.tno" readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="教师名">
                                <el-input v-model="form.tn"></el-input>
                            </el-form-item>
                            <el-form-item label="性别">
                                <el-input v-model="form.gender"></el-input>
                            </el-form-item>
                            <el-form-item label="办公室">
                                <el-input v-model="form.office"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click=" dialogFormVisible_update = false; clear_form();">取 消</el-button>
                            <el-button type="primary" @click=" handleUpdate(); clear_form();">确 定</el-button>
                        </div>
                    </el-dialog>

                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                        :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper" :total="totalItems">
                    </el-pagination>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            dialogFormVisible_add: false,
            dialogFormVisible_update: false,
            updateIndex: "",
            tableData: [],
            // tableData: [{"sno":"0433","sn":"张艳","gender":"女","maj":"生物04","birth":"1986-09-13","tel":null,"image":"https://web-example-lz.oss-cn-hangzhou.aliyuncs.com/2.jpg"},{"sno":"0496","sn":"李越","gender":"男","maj":"电子04","birth":"1984-02-23","tel":"13812901234","image":null},{"sno":"0529","sn":"赵欣","gender":"男","maj":"会计05","birth":"1984-01-27","tel":"13502221234","image":null},{"sno":"0531","sn":"张志国","gender":"男","maj":"生物05","birth":"1986-09-10","tel":"13312561234","image":null},{"sno":"0538","sn":"于兰兰","gender":"女","maj":"生物05","birth":"1984-02-20","tel":"13312001234","image":null},{"sno":"0591","sn":"王丽丽","gender":"女","maj":"电子05","birth":"1984-03-20","tel":"13320801234","image":null},{"sno":"0592","sn":"王海强","gender":"男","maj":"电子05","birth":"1986-11-01","tel":null,"image":null}],
            searchForm: {
                tno: "",
                tn: ""
            },
            form: {
                tno: "",
                tn: "",
                gender: "",
                office: ""
            },
            totalItems: 0,
            pageSize: 5,
            currentPage: 1,
        };
    },
    methods: {
        // 条件搜索
        handleSearch() {
            this.fetchData();
            this.search_success();
        },

        //添加信息
        handleAdd() {
            axios.post("/api/admin/teacher", this.form).then(() => {
                this.dialogFormVisible_add = false;
                this.tableData.push(this.form);
                this.add_success();
                this.fetchData();
            })
        },

        // 获取行中的数据，并导入到修改表格中
        handleEdit(index) {
            this.updateIndex = index;
            this.form = Object.assign({}, this.tableData[index]);
            this.dialogFormVisible_update = true;
        },

        // 提交修改过后的数据
        handleUpdate() {
            axios.put("/api/admin/teacher/update", this.form).then(() => {
                this.tableData.splice(this.updateIndex, 1, this.form);
                this.dialogFormVisible_update = false;
                this.update_success();
                this.fetchData();
            })
        },

        //删除
        handleDelete(index) {
            var tno_ = this.tableData[index].tno;
            axios.delete("/api/admin/teacher?tno=" + tno_).then(() => {
                this.tableData.splice(index, 1);
                this.delete_success();
            })
        },

        //改变每页的条数
        handleSizeChange(val) {
            this.pageSize = val;
            console.log(`每页 ${this.pageSize} 条`);
            this.fetchData();
        },

        //改变当前页数
        handleCurrentChange(val) {
            this.currentPage = val;
            console.log(`当前页: ${this.currentPage}`);
            this.fetchData();
        },

        // 分页获取数据
        fetchData() {
            axios.get("/api/admin/teacher/page", {
                params: {
                    tno: this.searchForm.tno,
                    tn: this.searchForm.tn,
                    page: this.currentPage,
                    pageSize: this.pageSize
                }
            }).then(res => {
                this.tableData = res.data.data.items;
                this.totalItems = res.data.data.total;
            })
        },

        clear_form() {
            this.form.tn = "";
            this.form.tno = "";
            this.form.gender = "";
            this.form.office = "";
        },

        delete_success() {
            this.$message({
                message: '删除成功',
                type: 'success'
            });
        },
        update_success() {
            this.$message({
                message: '修改成功',
                type: 'success'
            });
        },
        add_success() {
            this.$message({
                message: '添加成功',
                type: 'success'
            });
        },
        search_success() {
            this.$message({
                message: '搜索成功',
                type: 'success'
            });
        }
    },
    mounted() {
        this.fetchData();
    }
}
</script>

<style></style>