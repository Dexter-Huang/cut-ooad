<template>
  <div style="margin: 0px">
    <div id="pic">
      <img  src="./VCG41N11397068451.jpg" width="1480">
    </div>
    <div id="intro">
      Royal Holiday
    </div>
    <div id="intro1">
      <b>Hotel</b>
    </div>
    <div id="intro2">
      <b>Stay, Art, Fashion, Food & Music</b>
    </div>
    <div id="intro3">
      <b>For new culture</b>
    </div>
    <button @click="getTo()" id="border-btn" style="display:block;margin:0 auto" type="button" >
      Book Now ↓
    </button>
    <div id="introduce">
      <b>&nbsp;&nbsp;Royal hotel is a place that embodies the brand concept of</b>
      <br>
      <b>「stay, art, fashion, food & music for new culture」</b>
      <br>
      <b>&nbsp;&nbsp;Royal hotel is a hotel designed from a global perspective focusing on "present", "future" and "world"</b>
      <br>
      <b>&nbsp;&nbsp;Centered on the key words of stay&nbsp;/&nbsp;fashion&nbsp;/&nbsp;foot&nbsp;&&nbsp;music</b>
      <br>
      <b>&nbsp;&nbsp;Daily and non daily merge and produce new culture</b>
    </div>
    <div class="common-layout">
      <el-row id="111">
        <el-col>
          <el-container style="text-align: center">
            <el-main style="text-align: center;">
              <el-carousel height="30rem"  >
                <el-carousel-item v-for="item in this.carousel" :key="item">
                  <el-image
                            :src="item"
                            style="height: 30rem"/>
                </el-carousel-item>
              </el-carousel>
            </el-main>
            <el-footer height="10rem" style="">
              待补充
            </el-footer>
          </el-container>
        </el-col>
      </el-row>
    </div>

    <div class="affix-container" id="there">
      <el-row>
        <el-tabs type = "border-card" style="width: 100%; color: burlywood; border-radius: 50px; margin-bottom: 20px; margin-top: 20px; background-color: white">
          待补充
        </el-tabs>
      </el-row>
    </div>

    <el-row>
      <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
        <el-tabs type="border-card" style="height: 90rem">
          <el-tab-pane label="推荐">
            <el-row>
              <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8" style="padding-left: 1rem">
                <el-card  :body-style="{ padding: '20px' }" style="height: 38rem;">

                </el-card>
                <br/>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="深圳本地">
            <el-row>
              <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8" style="padding-left: 1rem">
                <el-card  :body-style="{ padding: '20px' }" style="height: 38rem;">
                </el-card>
                <br/>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="广东本地">
            <el-row>
              <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8" style="padding-left: 1rem">
                <el-card  :body-style="{ padding: '20px' }" style="height: 38rem;" >
                </el-card>
                <br/>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="休闲度假">
            <el-row>
              <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8" style="padding-left: 1rem">
                <el-card  :body-style="{ padding: '20px' }" style="height: 38rem;">
                </el-card>
                <br/>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="SUSTech">
            <el-row>
              <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8" style="padding-left: 1rem">
                <el-card  :body-style="{ padding: '20px' }" style="height: 38rem;" >
                </el-card>
                <br/>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
        <Map ></Map>
      </el-col>
    </el-row>

  </div>
  <br>
  <br>
  <div id="end">
    <div id="logo1">
      R<br>
      o<br>
      y<br>
      a<br>
      l<br>
    </div>
    <div id="logo2">
      H<br>
      o<br>
      t<br>
      e<br>
      l<br>
    </div>
    <div id="content1">
      <a style="color: white" href="">Stay</a><br>
      <a style="color: white" href="">Dine</a><br>
      <a style="color: white" href="">Packages</a><br>
      <a style="color: white" href="">Experiences</a><br>
      <a style="color: white" href="">Discover</a><br>
      <a style="color: white" href="">Weddings</a><br>
    </div>
    <div id="content2">
      <a style="color: white" href="">Festive</a><br>
      <a style="color: white" href="">Meet</a><br>
      <a style="color: white" href="">Our Story</a><br>
      <a style="color: white" href="">News</a><br>
      <a style="color: white" href="">COVID-19</a><br>
    </div>
    <div id="contact">
      CONTACT US : Tel&nbsp;&nbsp;0947-65-4865<br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mail&nbsp;&nbsp;RoyalHinfo@loof.inc
      <br>
      <br>
      <img src="./df701dfe-8280-4ff0-b18c-b9cdafc3c9de.jpg" width="150">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="./df701dfe-8280-4ff0-b18c-b9cdafc3c9de.jpg" width="150">
      <br>
      Official&nbsp;account&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;APP
      <br>
      <br>
      Business Hours : 8:00 - 22:00
    </div>
    <div id="sx"></div>
  </div>
</template>

<script>
import axios from "axios";
import { ref } from 'vue';
import Map from '../map/Map'
export default {
  name: "HotelInfo",
  components:{
    Map
  },
  created() {

  },
  data(){
    return {
      //Carousel=====
      carousel:[
        'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/cal1.jpg',
        'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/cal2.jpg',
        'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/cal3.jpg',
        'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/cal4.jpg',
        'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/cal5.jpg',
      ],
    }
  },
  methods:{
    getTo(){
      document.getElementById("there").scrollIntoView();
    },
    getImgArray(item){
      let arr=[]
      arr.push(item)
      return arr
    },

  }
}
</script>

<style scoped>
  .el-tag{
    white-space: normal;
    height:auto;
  }

.item {
  margin-top: 3px;
  margin-right: 6px;
}
.el-col {
  border-radius: 4px;
  flex-wrap: wrap !important ;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}

</style>

<style>
a{ text-decoration:none;  }
#pic {
  width: 100%;
  height: 100vh;
  z-index: 0;

  /*background: url("./VCG21gic15275010.jpg") center center no-repeat;*/
  /*background-size: 100% 100%;*/
  text-align: center;
  /*position:absolute;*/
}

#intro{
  font-family: "Microsoft JhengHei Light";
  position: relative;
  font-size: 35px;
  z-index: 2;
  text-align: center;
  top: -480px;
  color: white;
}

#intro1{
  font-family: "Microsoft JhengHei Light";
  position: relative;
  font-size: 35px;
  z-index: 3;
  text-align: center;
  top: -490px;
  color: white;
}

#intro2{
  font-family: "Microsoft JhengHei Light";
  position: relative;
  font-size: 35px;
  z-index: 3;
  text-align: center;
  top: -460px;
  color: white;
}
#intro3{
  font-family: "Microsoft JhengHei Light";
  position: relative;
  font-size: 35px;
  z-index: 3;
  text-align: center;
  top: -470px;
  color: white;
}
#border-btn {
  font-family: "Microsoft JhengHei Light";
  font-size: 16px;
  position: relative;
  top: -400px;
  z-index: 3;
  background-color:transparent;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 6vh;
  border-width:0px;
  backdrop-filter: blur(50px)
}

button {
  background: transparent;
  text-align: center;
  color: white;
  padding: 10px 36px;
  outline: none;
  overflow: hidden;
  position: relative;
}

span {
  z-index: 20;
}

button:after {
  content: '';
  display: block;
  position: absolute;
  top: -36px;
  left: -100px;
  background: white;
  width: 50px;
  height: 125px;
  opacity: 20%;
  transform: rotate(-45deg);
}

button:hover:after {
  left: 120%;
  transition: all 1000ms cubic-bezier(0.3, 1, 0.2, 1);
  -webkit-transition: all 1000ms cubic-bezier(0.3, 1, 0.2, 1);
}
#213{
  position: absolute;
}
#introduce{
  position: relative;
  font-family: "Microsoft JhengHei Light";
  font-size: 20px;
  top: -70px;
  left: 100px;
  line-height: 40px;
}
#111{
  text-align: center;
  background-color: #fff;
  border-radius: 20px;
  width: 300px;
  height: 350px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
#end{
  background-color: #363d40;
  height:700px;
}
#logo1{
  position: relative;
  font-family: "Adobe Caslon Pro Bold";
  font-size: 25px;
  color: white;
  top: 120px;
  left: 150px;
}
#logo2{
  position: relative;
  font-family: "Adobe Caslon Pro Bold";
  font-size: 25px;
  color: white;
  top: 00px;
  left: 180px;
}
#content1{
  position: relative;
  font-family: "Adobe Caslon Pro Bold";
  font-size: 20px;
  color: white;
  top: -217px;
  left: 310px;
  line-height: 50px;
}
#content2{
  position: relative;
  font-family: "Adobe Caslon Pro Bold";
  font-size: 20px;
  color: white;
  top: -518px;
  left: 460px;
  line-height: 50px;
}
#sx{
  position: relative;
  float:left;
  margin-top: 30px;
  width: 1px;
  height: 700px;
  background: darkgray;
  margin-left: 800px;
  top: -1377px;
  left: -150px;
}
#contact{
  position: relative;
  font-family: "Adobe Caslon Pro Bold";
  font-size: 20px;
  color: white;
  top: -768px;
  left: 740px;
  line-height: 50px;
}
</style>

