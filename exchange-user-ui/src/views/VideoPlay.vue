<template>
	<div>
		<vue3VideoPlay
						width="800px"
						title="钢铁侠"
						:src="this.videoPath"
						:poster="options.poster"
						@play="onPlay"
						@pause="onPause"
						@timeupdate="onTimeupdate"
						@canplay="onCanplay"

		/>
	</div>

</template>


<script>
    import { reactive } from 'vue';
		export default {
			props:["videoPath"],
			created() {
				console.log("this.videoPath---")
				console.log(this.videoPath)
				this.videoPath="`"+"https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/11.mp4"+"`"
			},
			data(){
				return{
					options : reactive({
						src: this.videoPath, //视频源
						poster: '', //封面
					}),
				}
			},
			methods:{
				onPlay(ev) {
					console.log('播放')
				}
			},
		}

    // const onPause = (ev) => {
    //     console.log(ev, '暂停')
    // }
		//
    // const onTimeupdate = (ev) => {
    //     console.log(ev, '时间更新')
    // }
    // const onCanplay = (ev) => {
    //     console.log(ev, '可以播放')
    // }
</script>

<style scoped>
</style>
