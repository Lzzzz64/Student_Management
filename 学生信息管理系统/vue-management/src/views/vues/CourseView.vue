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
                        <el-form-item label="课程号：">
                            <el-input v-model="searchForm.cno" placeholder="课程号"></el-input>
                        </el-form-item>
                        <el-form-item label="课程名：">
                            <el-input v-model="searchForm.cn" placeholder="课程名"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="handleSearch">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="dialogFormVisible_add = true"
                                icon="el-icon-edit"></el-button>
                        </el-form-item>
                    </el-form>



                    <el-table :data=" tableData " border style="width: 100%">
                        <el-table-column fixed prop="cno" label="课程号" width="140">
                        </el-table-column>
                        <el-table-column prop="cn" label="课程名" width="140">
                        </el-table-column>
                        <el-table-column prop="score" label="学分" width="140">
                        </el-table-column>
                        <el-table-column prop="time" label="学时" width="140">
                        </el-table-column>
                        <el-table-column prop="teacher_tno" label="授课教师号" width="150">
                        </el-table-column>
                        <el-table-column prop="classroom" label="教室" width="170">
                        </el-table-column>
                        <el-table-column prop="number" label="剩余选课人数" width="150">
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
                    <el-dialog title="添加课程信息" :visible.sync=" dialogFormVisible_add ">
                        <el-form :ref=" form " model="form">
                            <el-form-item label="课程号">
                                <el-input v-model=" form.cno "></el-input>
                            </el-form-item>
                            <el-form-item label="课程名">
                                <el-input v-model=" form.cn "></el-input>
                            </el-form-item>
                            <el-form-item label="学分">
                                <el-input v-model=" form.score "></el-input>
                            </el-form-item>
                            <el-form-item label="学时">
                                <el-input v-model=" form.time "></el-input>
                            </el-form-item>
                            <el-form-item label="授课教师号">
                                <el-input v-model=" form.teacher_tno "></el-input>
                            </el-form-item>
                            <el-form-item label="教室">
                                <el-input v-model=" form.classroom "></el-input>
                            </el-form-item>
                            <el-form-item label="剩余选课人数">
                                <el-input v-model=" form.number "></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click=" dialogFormVisible_add = false; clear_form(); ">取 消</el-button>
                            <el-button type="primary" @click=" handleAdd(); clear_form();">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!-- 修改信息框 -->
                    <el-dialog title="修改课程信息" :visible.sync=" dialogFormVisible_update ">
                        <el-form :ref=" form " model="form">
                            <el-form-item label="课程号">
                                <el-input v-model=" form.cno " readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="课程名">
                                <el-input v-model=" form.cn "></el-input>
                            </el-form-item>
                            <el-form-item label="学分">
                                <el-input v-model=" form.score "></el-input>
                            </el-form-item>
                            <el-form-item label="学时">
                                <el-input v-model=" form.time "></el-input>
                            </el-form-item>
                            <el-form-item label="授课教师号">
                                <el-input v-model=" form.teacher_tno "></el-input>
                            </el-form-item>
                            <el-form-item label="教室">
                                <el-input v-model=" form.classroom "></el-input>
                            </el-form-item>
                            <el-form-item label="剩余选课人数">
                                <el-input v-model=" form.number "></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click=" dialogFormVisible_update = false; clear_form();">取 消</el-button>
                            <el-button type="primary" @click=" handleUpdate(); clear_form(); ">确 定</el-button>
                        </div>
                    </el-dialog>
                    <el-pagination @size-change=" handleSizeChange " @current-change=" handleCurrentChange "
                        :current-page=" currentPage " :page-sizes=" [5, 10, 15, 20] " :page-size=" pageSize "
                        layout="total, sizes, prev, pager, next, jumper" :total=" totalItems ">
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
            // tableData: [{ "cno": "K001", "cn": "计算机图形学", "score": 5.0, "time": 40, "teacher": "胡晶晶" }, { "cno": "K002", "cn": "计算机应用基础", "score": 6.0, "time": 48, "teacher": "任泉" }, { "cno": "K006", "cn": "数据结构", "score": 8.0, "time": 64, "teacher": "马跃先" }, { "cno": "M001", "cn": "政治经济学", "score": 8.0, "time": 64, "teacher": "孔繁新" }, { "cno": "S001", "cn": "高等数学", "score": 6.0, "time": 48, "teacher": "赵晓尘" }],
            tableData: [],
            form: {
                cno: "",
                cn: "",
                score: "",
                time: "",
                classroom: "",
                teacher_tno: "",
                number: "",
            },
            searchForm: {
                cn: "",
                cno: ""
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
            axios.post("/api/admin/course", this.form).then(() => {
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
            this.fetchData();
        },

        // 提交修改过后的数据
        handleUpdate() {
            axios.put("/api/admin/course/update", this.form).then(() => {
                this.tableData.splice(this.updateIndex, 1, this.form);
                this.dialogFormVisible_update = false;
                this.update_success();
                this.fetchData();
            })
        },

        //删除
        handleDelete(index) {
            var cno_ = this.tableData[index].cno;
            axios.delete("/api/admin/course?cno=" + cno_).then(() => {
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
            // const url = `http://localhost:8080/course/pageItemList?page=${this.currentPage}&size=${this.pageSize}`;
            // axios.get(url).then(res => {
            //     this.tableData = res.data.data.items;
            //     this.totalItems = res.data.data.total;
            // })
            axios.get("/api/admin/course/page", {
                params: {
                    cno: this.searchForm.cno,
                    cn: this.searchForm.cn,
                    teacher: this.searchForm.teacher,
                    page: this.currentPage,
                    pageSize: this.pageSize
                }
            }).then(res => {
                this.tableData = res.data.data.items;
                this.totalItems = res.data.data.total;
            })
        },

        clear_form() {
            this.form.cn = "";
            this.form.cno = "";
            this.form.score = "";
            this.form.time = "";
            this.form.teacher = "";
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