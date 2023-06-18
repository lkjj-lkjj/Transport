<template>
  <div style="margin: 100px; padding-right: 200px;">
    <el-table :data="tableData" height="600" style="width: 100%">
      <el-table-column prop="ordername" label="Name" width="180" />
      <el-table-column prop="description" label="Description" width="180" />
      <el-table-column prop="createtime" label="Create time" />
      <el-table-column prop="userid" label="Userid" />
      <el-table-column prop="transporterid" label="TransporterId" />
      <el-table-column prop="starttime" label="Take order time" />
      <el-table-column label="State">In transit</el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "InTransit",
  data(){
    return{
      tableData: []
    }
  },
  mounted() {
    request.get("/secure/intrans/"+sessionStorage.getItem('id')).then(res=>{
      this.tableData = res.data
    })
  }
}
</script>

<style scoped>

</style>