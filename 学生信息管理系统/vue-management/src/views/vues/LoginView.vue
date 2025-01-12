<template>
    <div>
        <el-container>
            <el-header>管理员登录</el-header>
            <el-container>
                <el-main>
                    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-rule">
                        <el-form-item label="账号">
                            <el-input v-model="ruleForm.account"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input v-model="ruleForm.password"></el-input>
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
                account: "",
                password: ""
            },
        };
    },
    methods: {
        submit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // const url = `http://localhost:8080/login?sno=${this.ruleForm.sno}&sn=${this.ruleForm.sn}`;
                    const url = `/api/admin/administrator/login`;
                    // const url = '/api';
                    // console.log("1");
                    axios.post(url, this.ruleForm).then(res => {
                        if (res.data.code == "1") {
                            // console.log("2");
                            this.login_success();
                            this.$router.push({ path: '/student' });
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