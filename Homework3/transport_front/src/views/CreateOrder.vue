<template>
  <div style="margin: 100px; padding-right: 200px;">
    <div style="border: 1px solid gainsboro; border-radius: 10px; padding: 70px">
      <el-form :model="form" label-width="120px">
        <el-form-item label="Order name">
          <el-input v-model="form.ordername" />
        </el-form-item>
        <el-form-item label="Order Type">
          <el-select v-model="form.ordertype" placeholder="Select your order type">
            <el-option label="Private" value="private" />
            <el-option label="Enterprise" value="enterprise" />
          </el-select>
        </el-form-item>
        <el-form-item label="Create time">
          <el-col :span="11">
            <el-date-picker
                v-model="form.date1"
                type="date"
                placeholder="Pick a date"
                style="width: 100%"
                value-format="YYYY-MM-dd"
            />
          </el-col>
          <el-col :span="2" class="text-center">
            <span class="text-gray-500"></span>
          </el-col>
          <el-col :span="11">
            <el-time-picker
                v-model="form.date2"
                placeholder="Pick a time"
                style="width: 100%"
                value-format="HH:mm:ss"
            />
          </el-col>
        </el-form-item>
        <el-form-item label="Anonymous">
          <el-switch v-model="form.anonymous" />
        </el-form-item>
        <el-form-item label="Transport type">
          <el-checkbox-group v-model="form.transporttype">
            <el-checkbox label="Plane" name="type" />
            <el-checkbox label="Train" name="type" />
            <el-checkbox label="Truck" name="type" />
            <el-checkbox label="Ship" name="type" />
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="Description">
          <el-input v-model="form.description" type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit()">Create</el-button>
          <el-button @click="clear()">Clear</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "CreateOrder",
  data(){
    return{
      form: {
        ordername: '',
        createtime: '',
        date1: '',
        date2: '',
        description: '',
        ordertype: '',
        anonymous: '',
        userid: sessionStorage.getItem('id')
      }
    }
  },
  methods:{
    onSubmit(){
      this.form.createtime = this.form.date1 + " " +this.form.date2
      request.post("/user-service/secure/insert", this.form).then(res=>{
        if(res.code === "200"){
          this.$message({
            type: "success",
            message: "Success"
          })
        }else{
          this.$message({
            type: "error",
            message: "False"
          })
        }
      })
    },
    clear(){
      this.form = {}
    }
  },
  mounted() {
    if(sessionStorage.getItem("id") === null){
      sessionStorage.setItem("id", 9)
      sessionStorage.setItem("username", "lkjjjj")
      sessionStorage.setItem("auth",0)
    }
  }
}
</script>

<style scoped>

</style>