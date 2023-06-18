<template>
  <div style="height: 60px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex;">
    <div style="width: 500px; padding-left: 20px; font-weight: bold; color: #409EFF; font-size: x-large">Transport System</div>
    <div style="flex: 1"></div>
    <div style="width: 130px; padding-top: 15px;">
      <el-dropdown>
        <el-button type="primary">
          <el-icon><User /></el-icon><div style="width: 8px"></div>{{this.name}}
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('person')"><el-icon><User /></el-icon>My information</el-dropdown-item>
            <el-dropdown-item @click="$router.push('modifyPassword')"><el-icon><Edit /></el-icon>Modify Password</el-dropdown-item>
            <el-dropdown-item @click="logout"><el-icon><CloseBold /></el-icon>Logout</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import {User, Edit, CloseBold} from "@element-plus/icons";
export default {
  name: "Header",
  components: {User, Edit, CloseBold},
  methods:{
    logout(){
      sessionStorage.clear()
      router.push("/login")
    }
  },
  data(){
    return{
      name: sessionStorage.getItem("username")
    }
  }
}
</script>

<style scoped>
</style>