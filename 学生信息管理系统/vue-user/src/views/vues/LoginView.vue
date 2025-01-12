<template>
    <div>
        <el-container>
            <el-header>学生登录</el-header>
            <el-container>
                <el-main>
                    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-rule">
                        <el-form-item label="姓名">
                            <el-input v-model="ruleForm.sn"></el-input>
                        </el-form-item>
                        <el-form-item label="学号">
                            <el-input v-model="ruleForm.sno"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submit('ruleForm')">登录</el-button>
                            <el-button @click="reset('ruleForm')">重置</el-button>
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
            ruleForm: {
                sno: "",
                sn: ""
            },
        };
    },
    methods: {
        submit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // const url = `http://localhost:8080/login?sno=${this.ruleForm.sno}&sn=${this.ruleForm.sn}`;
                    const url = `/api/user/student/login`;
                    // const url = '/api';
                    // console.log("1");
                    axios.post(url, this.ruleForm).then(res => {
                        if (res.data.code == "1") {
                            this.login_success();
                            window.sessionStorage.setItem('sno', this.ruleForm.sno)
                            window.sessionStorage.setItem('sn', this.ruleForm.sn)
                            this.$router.push({ path: '/chooseCourse' });
                        } else {
                            this.login_error();
                        }
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },

        reset(formName) {
            this.$refs[formName].resetFields();
        },
        login_success() {
            this.$message({
                message: '登录成功',
                type: 'success'
            });
        },
        login_error() {
            this.$message({
                message: '登录失败',
                type: 'error'
            });
        }
    }
}
</script>

<style></style>