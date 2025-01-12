<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <!-- 头部 -->
            <el-header style="font-size:40px;background-color: rgb(238, 241, 246)">学生信息管理系统 <el-button type="danger"
                    @click="handleExit">退出</el-button></el-header>

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
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="学号">
                            <el-input v-model="form.sno" readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名">
                            <el-input v-model="form.sn" readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-input v-model="form.gender" readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="专业班级">
                            <el-input v-model="form.maj" readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="宿舍地址">
                            <el-input v-model="form.address" readonly="true"></el-input>
                        </el-form-item>
                    </el-form>
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
            searchForm: {
                dno: "",
                address: ""
            },
            form: {
                sno: "",
                sn: "",
                gender: "",
                maj: "",
                address: "",
            },
            sno: window.sessionStorage.getItem('sno'),
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

        // 获取数据
        fetchData() {
            axios.get("/api/user/student?sno="+ this.sno).then(res => {
                this.form = res.data.data;
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
    },

    mounted() {
        this.fetchData();
    }
}
</script>

<style></style>