<template>
  <nav>
    <Header></Header>
    <div style="display: flex">
      <div style="padding-left: 20px; padding-top: 20px;width: 300px">
        <Asidegood />
      </div>
      <router-view style="flex: 1"/>
    </div>
  </nav>
</template>

<script>
import Header from "@/components/Header.vue";
import Asidegood from "@/components/Asidegood.vue";
export default {
  name: "Home",
  components: {Header ,Asidegood}
}
</script>

<style scoped>

</style>