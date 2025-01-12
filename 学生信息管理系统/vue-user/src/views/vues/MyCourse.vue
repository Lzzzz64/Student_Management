<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <!-- 头部 -->
            <el-header style="font-size:40px;background-color: rgb(238, 241, 246)">
                学生信息管理系统
                <el-button type="danger" @click="handleExit">退出</el-button>
            </el-header>

            <!-- 边框 -->
            <el-container>
                <el-aside width="200px">
                    <el-menu>
                        <el-menu-item>
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/chooseCourse">选课功能</router-link></template>
                        </el-menu-item>
                        <el-menu-item>
                            <template slot="title"><i class="el-icon-setting"></i><router-link
                                    to="/chooseDormitory">选宿舍功能</router-link></template>
                        </el-menu-item>
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>个人信息</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item><router-link to="/myCourse">个人选课情况</router-link></el-menu-item>
                                <el-menu-item><router-link to="/myInformation">个人详情情况</router-link></el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>

                <!-- 主页 -->
                <el-main>
                    <el-table :data="tableData" border style="width: 100%">
                        <el-table-column fixed prop="cno" label="课程号" width="160">
                        </el-table-column>
                        <el-table-column prop="cn" label="课程名" width="170">
                        </el-table-column>
                        <el-table-column prop="score" label="学分" width="160">
                        </el-table-column>
                        <el-table-column prop="time" label="学时" width="160">
                        </el-table-column>
                        <el-table-column prop="teacher_tno" label="授课教师号" width="170">
                        </el-table-column>
                        <el-table-column prop="classroom" label="教室" width="200">
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="160">
                            <template slot-scope="scope">
                                <!-- <el-button @click="handleDelete(scope.$index)" type="danger" size="small">删除</el-button> -->
                                <template>
                                    <el-popconfirm title="确定退选吗？" @confirm=" handleOut(scope.$index)">
                                        <el-button type="primary" slot="reference">退选</el-button>
                                    </el-popconfirm>
                                </template>
                            </template>
                        </el-table-column>
                    </el-table>

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
            sn: window.sessionStorage.getItem('sn'),
            tableData: [],
            form: {
                cno: "",
                sno: window.sessionStorage.getItem('sno')
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

        //退课
        handleOut(index) {
            // this.check_token();
            this.form.cno = this.tableData[index].cno;
            axios.post("/api/user/studentCourse/out", this.form).then(res => {    
                if (res.data.code == "1") {
                        this.out_success();
                        this.fetchData();
                    } else {
                        this.out_error();
                    }

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
            axios.get("/api/user/studentCourse/pageBySno", {
                params: {
                    sno: this.form.sno,
                    page: this.currentPage,
                    pageSize: this.pageSize
                }
            }).then(res => {
                this.tableData = res.data.data.items;
                this.totalItems = res.data.data.total;
            })
        },

        //退出登录
        handleExit() {
            window.sessionStorage.removeItem('sno');
            window.sessionStorage.removeItem('sn');
            this.$router.push({ path: '/' });
        },

        //检查token是否有值
        check_token() {
            if (window.sessionStorage.getItem('token') == null) {
                this.Unloading();
                this.$router.push({ path: '/' });
            }
        },

        out_success() {
            this.$message({
                message: '退课成功',
                type: 'success'
            });
        },

        out_error() {
            this.$message({
                message: '退课失败',
                type: 'error'
            });
        },
    },
    mounted() {
        this.fetchData();
    }
}
</script>

<style></style>