<template>
  <div class="common-layout" style="opacity: 0.85">
    <el-row>
      <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="20" >
        <el-row style="margin-right: 0.5rem;opacity: 0.85;">
          <UserHeaderPage style="width: 100%;"></UserHeaderPage>
        </el-row>
        <el-row  style="margin-top: 0.5rem;margin-right: 0.5rem">
          <el-card style="margin-right: 0.5rem;width: 100%;opacity: 0.85">
            <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
              <el-tab-pane label="全部礼品卡" name="first"><UserGiftPane :type="0"/></el-tab-pane>
              <el-tab-pane label="未过期" name="second"><UserGiftPane :type="1"/></el-tab-pane>
              <el-tab-pane label="已过期" name="third"><UserGiftPane :type="2"/></el-tab-pane>
              <el-tab-pane label="已使用" name="fourth"><UserGiftPane :type="3"/></el-tab-pane>
            </el-tabs>
          </el-card>
        </el-row>

      </el-col>
      <el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="4">
        <RightSide></RightSide>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import UserHeaderPage from "@/views/user/userHeaderPage";
import RightSide from "@/views/RightSide"
import UserGiftPane from '@/views/user/userGift/userGiftPane'
export default {
  name: "userGift",
  components:{UserHeaderPage,RightSide,UserGiftPane},
  data(){
    return{
      activeName:'first',
    }
  },

}
</script>

<style scoped>
.common-layout /deep/ {
  background:url("../userGift/coupon.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
}

</style>
