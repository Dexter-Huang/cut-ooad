<template>
	<div>
		<el-table v-fit-columns
							ref="multipleTable"
							@selection-change="handleSelectionChange"
							tooltip-effect="dark"
							:data="tableData"
							:cell-style="{color: '#666'}"
							:header-cell-style="{background:'#f0f9eb','text-align':'center'}"
							border fit highlight-current-row style="width: 100%;margin: auto">
			<el-table-column type="expand" >
				<template #default="scope">
					<div style="text-align: center">
						<el-card style=" color: #333">

						</el-card>
					</div>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template #default="{ row }">
					<div style="text-align: center" >
						<el-button type="danger" size="small" >支付订金</el-button>
					</div>
				</template>
			</el-table-column>
		</el-table>

		<el-dialog v-model="this.billVisible">
			<h2 style="text-align: center">订单详情</h2>
			<el-form :model="bill">
			</el-form>
		</el-dialog>

		<el-dialog v-model="dialogVisible" title="Confirm" width="30%" center>
			<template #footer>
      <span class="dialog-footer">
        <el-button @click="alipayOp()">支付宝支付</el-button>
        <el-button type="primary" @click="leftOp()">
          账户余额支付
        </el-button>
				<el-button @click="dialogVisible = false;">取消支付</el-button>

      </span>
			</template>
		</el-dialog>

		<div style="text-align: center;">
			<el-pagination class="mpage" layout="prev, pager, next"
										 :current-page="currentPage"
										 :page-size="pageSize"
										 :total="total"
										 @current-change=page />
		</div>

	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		name: "userBillPane",
		props:["type"],
		data(){
			return{
				dialogVisible:false,
				isAlipay:2,
				cur_row:'',
				x:-1,
				tableData:[],
				currentPage: 1,
				total: 0,
				pageSize: 1,
				accountId:0,
				billVisible:false,
				bill:{
					accountId:0, accountName:'', accountPhone:'', billNo:'', createTime:'',
					extraInfo:'', hotelAddress:'', hotelId:0, hotelImg:'',hotelName:'',
					id:0, likeNum:0, payChanel:0, payTime:'',
					reviewId:0, roomCount:0, roomNo:0, roomPrice:0,
					roomTitle:'', seckillEventTitle:'', status:'', uuid:'',
				},
			}
		},
		created() {
			this.accountId=this.$store.getters.getUser.id;
			this.page(1)
		},
		methods: {

			page(currentPage) {

			},
		}
	}
</script>

<style lang="scss">
	//如果是单独设置表头换行

	.el-table th>.cell {
		white-space: pre-wrap;
	}


	//或者全局换行
	.el-table{
		.cell {
			white-space: pre-wrap;
		}
	}

	.el-tag{
		white-space: normal;
		height:auto;
	}
</style>
