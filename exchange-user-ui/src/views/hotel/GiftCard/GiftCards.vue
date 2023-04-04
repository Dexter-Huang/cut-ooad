<template>
	<div>
		<el-main style="text-align: center;margin-top: 0">
			<el-carousel height="35rem"  >
				<el-carousel-item v-for="item in this.calrousel" :key="item">
					<el-image :src="item" style="height: 30rem"/>
				</el-carousel-item>
			</el-carousel>
		</el-main>
		<h2 style="text-align: center">Royal主题卡</h2>
		<el-row>
			<el-col :span="6">
				<el-card>
					<el-image style="height: 14rem" :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/royal.jpg`"></el-image>
				</el-card>
			</el-col>
			<el-col :span="1"></el-col>
			<el-col :span="17">
				<el-scrollbar>
					<div class="scrollbar-flex-content">
						<p v-for="item in themeList" :key="item" class="scrollbar-demo-item">
							<el-image :src="item.img"></el-image>
							<el-card style="width: 350px;height: 200px;">
								<el-tag>
									{{ item.name }}
								</el-tag>
								<el-tag>
									所需积分:{{ item.credit }}
								</el-tag>
								<el-button @click="getCard(item)">兑换礼品卡</el-button>
							</el-card>
						</p>
					</div>
				</el-scrollbar>
			</el-col>
		</el-row>
		<h2 style="text-align: center">佳节祝福卡</h2>
		<el-row>
			<el-col :span="6">
				<el-card>
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/festival.jpg`"></el-image>
				</el-card>
			</el-col>
			<el-col :span="1"></el-col>
			<el-col :span="17">
				<el-scrollbar>
					<div class="scrollbar-flex-content">
						<p v-for="item in festivalList" :key="item" class="scrollbar-demo-item">
							<el-image :src="item.img"></el-image>
							<el-card style="width: 350px;height: 200px;">
								<el-tag>
									{{ item.name }}
								</el-tag>
								<el-tag>
									所需积分:{{ item.credit }}
								</el-tag>
								<el-button @click="getCard(item)">兑换礼品卡</el-button>
							</el-card>
						</p>
					</div>
				</el-scrollbar>
			</el-col>
		</el-row>
		<h2 style="text-align: center">实体经典卡</h2>
		<el-row>
			<el-col :span="6">
				<el-card>
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/classic.jpg`"></el-image>
				</el-card>
			</el-col>
			<el-col :span="1"></el-col>
			<el-col :span="17">
				<el-scrollbar>
					<div class="scrollbar-flex-content">
						<p v-for="item in classicList" :key="item" class="scrollbar-demo-item">
							<el-image :src="item.img"></el-image>
							<el-card style="width: 350px;height: 200px;">
								<el-tag>
									{{ item.name }}
								</el-tag>
								<el-tag>
									所需积分:{{ item.credit }}
								</el-tag>
								<el-button @click="getCard(item)">兑换礼品卡</el-button>
							</el-card>
						</p>
					</div>
				</el-scrollbar>
			</el-col>
		</el-row>
		<h2 style="text-align: center">商务答谢卡</h2>
		<el-row>
			<el-col :span="6">
				<el-card>
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/bussi.jpg`"></el-image>
				</el-card>
			</el-col>
			<el-col :span="1"></el-col>
			<el-col :span="17">
				<el-scrollbar>
					<div class="scrollbar-flex-content">
						<p v-for="item in bussinessList" :key="item" class="scrollbar-demo-item">
							<el-image :src="item.img"></el-image>
							<el-card style="width: 350px;height: 200px;">
								<el-tag>
									{{ item.name }}
								</el-tag>
								<el-tag>
									所需积分:{{ item.credit }}
								</el-tag>
								<el-button @click="getCard(item)">兑换礼品卡</el-button>
							</el-card>
						</p>
					</div>
				</el-scrollbar>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import { ref } from 'vue'
	import axios from 'axios'

	export default {
		name: "GiftCards",
		data(){
			return{
				tabPosition : ref('left'),
				classicList:[],
				themeList:[],
				bussinessList:[],
				festivalList:[],
				accountId:parseInt(this.$store.getters.getUser.id),
				//calrousel
				calrousel:[
					'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/gifc.jpg',
					'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/gifc1.jpg',
					'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/gifc2.jpg',
					'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/gifc3.jpg'
				],

			}
		},
		created() {
			this.getBussinessList();
			this.getClassicList();
			this.getThemeList();
			this.getFestivalList();
			console.log("this.themeList")
			console.log(this.themeList)
			console.log(this.classicList)
			console.log(this.bussinessList)
			console.log(this.festivalList)
		},
		methods:{
			getThemeList(){
				axios.get('/giftcard/list/0').then(res=>{
					this.themeList= res.data.data
				})
			},
			getClassicList(){
				axios.get('/giftcard/list/1').then(res=>{
					this.classicList= res.data.data
				})
			},
			getBussinessList(){
				axios.get('/giftcard/list/2').then(res=>{
					this.bussinessList= res.data.data
				})
			},
			getFestivalList(){
				axios.get('/giftcard/list/3').then(res=>{
					this.festivalList= res.data.data
				})
			},
			getCard(item){
				console.log(item)
				axios.get('giftcard/getcard/'+item.id+'/'+this.accountId).then(res=>{
					if(res.data.code==200){
						this.$message.success(res.data.msg)
					}else{
						this.$message.warning(res.data.msg)
					}
				})
			},

		},
	}
</script>

<style scoped>
	.el-tag{
		white-space: normal;
		height:auto;
	}
	.scrollbar-flex-content {
		display: flex;
	}

	.scrollbar-demo-item {
		flex-shrink: 0;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 350px;
		height: 200px;
		margin: 10px;
		text-align: center;
		border-radius: 4px;
		background: var(--el-color-danger-light-9);
		color: var(--el-color-danger);
	}
</style>

