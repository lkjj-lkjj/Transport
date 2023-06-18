<template>
  <div style="margin: 100px; padding-right: 100px;">
    <el-table :data="tableData" height="600" style="width: 100%">
      <el-table-column prop="ordername" label="Name"/>
      <el-table-column prop="description" label="Description"/>
      <el-table-column prop="createtime" label="Create time" width="170"/>
      <el-table-column prop="userid" label="Userid" width="100"/>
      <el-table-column prop="transporterid" label="TransporterId" />
      <el-table-column prop="starttime" label="Take order time" width="170"/>
      <el-table-column prop="endtime" label="Completed time" width="170"/>
      <el-table-column label="State">Completed</el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "MyHistory",
  data(){
    return{
      tableData: []
    }
  },
  mounted() {
    request.get("/secure/myhistory/"+sessionStorage.getItem('id')).then(res=>{
      this.tableData = res.data
    })
  }
}
</script>

<style scoped>

</style>