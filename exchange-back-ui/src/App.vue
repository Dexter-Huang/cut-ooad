<template>
  <router-view/>
</template>

<style>


  html,body{margin: 0rem;padding: 0rem;width: 100%;height: 100%;}
  #app{
    /*width: 100%;*/
    /*height: 100%!important;*/
    /*margin: 0px;*/
    /*padding: 0px;*/
    /*position: absolute;*/
    background-size:100% 100%;

    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  #nprogress .bar {
    background: rgb(96, 47, 231) !important;
  }

  html {
    font-size: 40.5%;
  }
  @media only screen and (min-width: 481px){
    html {
      font-size: 59%!important;
    }
  }
  @media only screen and (min-width: 561px){
    html {
      font-size: 64%!important;
    }

  }
  @media only screen and (min-width: 641px){
    html {
      font-size: 75%!important;
    }
  }
  body{
    font-size:12px;
    font-size:1.2rem ;
  }
  p{
    font-size:14px;
    font-size:1.4rem;
  }


</style>
