<template>
  <div style="opacity: 0.85">
    <div style="opacity: 0.85" v-for="item in this.giftCard">
      <el-card class="box-card" >
        <el-image style="height: 15rem" :src="item.img"></el-image>
        <br/>
        <el-tag type="success" style="margin-right: 10px">
          {{ item.name }}
        </el-tag>
        <el-tag type="success" style="margin-right: 10px">
          描述:{{ item.description }}
        </el-tag>
        <el-tag type="success" style="margin-right: 10px">
          所需积分:{{ item.credit }}
        </el-tag>
      </el-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "userGiftPane",
  props:["type"],
  data(){
    return{
      giftCard:'',
      accountId:parseInt(this.$store.getters.getUser.id),
    }
  },
  created() {
    axios.get('/giftcard/getusercard/'+this.accountId+'/'+this.type).then(res=>{
      this.giftCard=res.data.data
    })
  },
  methods:{
    gotoGift(){
      this.$router.push('/giftCards')
    }
  },
}
</script>

<style scoped>
.box-card {
  border: 1px solid transparent;
  border-radius: 20px;
  background-color: rgba(255,255,255,0.1);
  box-shadow: 10px 10px 0 0 rgba(0, 0 ,0 , 0.2);

}
</style>
