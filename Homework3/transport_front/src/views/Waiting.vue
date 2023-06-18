<template>
  <div style="margin: 100px; padding-right: 200px;">
    <el-table :data="tableData" height="600" style="width: 100%">
      <el-table-column fixed prop="ordername" label="Name" width="150" />
      <el-table-column prop="description" label="Description" width="400" />
      <el-table-column  prop="createtime" label="Create Time" width="200" />
      <el-table-column prop="userid" label="User Id" width="120" />
      <el-table-column prop="state" label="State" width="200">
        <template #default="scope">
          <!-- 使用插槽和计算属性来动态显示不同的内容 -->
          <span>{{ getStateText(scope.row.state) }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button link type="primary" @click="handleClick(scope.row)">Revoke</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Waiting",
  data(){
    return{
      tableData: []
    }
  },
  mounted() {
    request.get("/user-service/secure/waiting/"+sessionStorage.getItem('id')).then(res=>{
      this.tableData = res.data
    })
  },
  methods:{
    getStateText(state) {
      // 根据状态的值返回相应的文本
      if (state === 0) {
        return 'Waiting be transported';
      } else if (state === 1) {
        return 'In transit';
      } else{
        return 'Transport complete'
      }
    },
    handleClick(row){
      request.post("/user-service/secure/revoke",row).then(res=>{
        if(res.code === "200"){
          this.$message({
            type: "success",
            message: "Success"
          })
          request.get("/user-service/secure/waiting/"+sessionStorage.getItem('id')).then(res=>{
            this.tableData = res.data
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>