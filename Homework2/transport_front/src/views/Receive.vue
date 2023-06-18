<template>
  <div style="margin: 100px; padding-right: 200px;">
    <el-table :data="tableData" height="600" style="width: 100%">
      <el-table-column fixed prop="ordername" label="Name" width="150" />
      <el-table-column prop="description" label="Description" width="300" />
      <el-table-column  prop="createtime" label="Create Time" width="200" />
      <el-table-column prop="userid" label="User Id" width="120"/>
      <el-table-column prop="state" label="State">Waiting be transported</el-table-column>
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button link type="primary" @click="handleClick(scope.row)">Receive</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Receive",
  data(){
    return{
      tableData: []
    }
  },
  mounted() {
    request.get("/secure/receive").then(res=>{
      this.tableData = res.data
    })
  },
  methods:{
    handleClick(row){
      row["transporterid"] = sessionStorage.getItem("id")
      request.post("/secure/receiveorder", row).then(res=>{
        if(res.code === "200"){
          this.$message({
            type: "success",
            message: "Success"
          })
          request.get("/secure/receive").then(res=>{
            this.tableData = res.data
          })
        }else{
          this.$message({
            type: "error",
            message: "False"
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>