<template>
  <div style="margin: 100px; padding-right: 200px;">
    <el-table :data="tableData" height="600" style="width: 100%">
      <el-table-column prop="ordername" label="Name" width="180" />
      <el-table-column prop="description" label="Description" width="180" />
      <el-table-column prop="createtime" label="Create time" width="170"/>
      <el-table-column prop="userid" label="Userid" />
      <el-table-column prop="transporterid" label="TransporterId" />
      <el-table-column prop="starttime" label="Take order time" width="170"/>
      <el-table-column label="State">In transit</el-table-column>
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button link type="primary" @click="handleClick(scope.row)">Complete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "MyInTrans",
  data(){
    return{
      tableData: []
    }
  },
  mounted() {
    request.get("/secure/myintrans/"+sessionStorage.getItem('id')).then(res=>{
      this.tableData = res.data
    })
  },
  methods:{
    handleClick(row){
      request.post("/secure/complete", row).then(res=>{
        if(res.code === "200"){
          this.$message({
            type: "success",
            message: "Success"
          })
          request.get("/secure/myintrans/"+sessionStorage.getItem('id')).then(res=>{
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