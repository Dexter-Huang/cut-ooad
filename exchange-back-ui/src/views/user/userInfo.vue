<template>
	<div>
		<h2>Royal用户信息表</h2>
		<div style="text-align: right">
			<el-button type="primary" size="small" @click="showDialog('add')">
				<el-icon><CirclePlusFilled /></el-icon>添加
			</el-button>
			<el-button type="primary" size="small" @click="exportJsonExcel(multipleSelection.length==0?tableData:multipleSelection)"><el-icon><CirclePlusFilled /></el-icon>批量下载</el-button>
			<el-button type="primary" size="small" @click="showDialog('updatelist')"><el-icon><CirclePlusFilled /></el-icon>批量更新</el-button>
			<el-button type="danger" size="small" @click="delList" ><el-icon><DeleteFilled /></el-icon>批量删除</el-button>
			<el-button type="primary" size="small" @click="showDialog('searchlist')"><el-icon><Search /></el-icon>多条件搜索</el-button>
			<!--			<el-button type="primary" size="small" @click="addlist"><el-icon><CirclePlusFilled /></el-icon>批量添加</el-button>-->
			<!--limit:最大上传数，on-exceed：超过最大上传数量时的操作  -->
			<el-upload
							class="upload-demo"
							:on-change="handleChange"
							:on-remove="handleRemove"
							:on-exceed="handleExceed"
							accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"
							:auto-upload="false">
				<el-button size="small" type="primary">批量上传添加</el-button>
			</el-upload>
		</div>
		<!-- 外层为用户组数据 -->
			<el-table v-fit-columns
								ref="multipleTable"
								@selection-change="handleSelectionChange"
								tooltip-effect="dark"
								:data="tableData"
								:cell-style="{color: '#666'}"
								:header-cell-style="{background:'#f0f9eb','text-align':'center'}"
								border fit highlight-current-row style="width: 100%;margin: auto">
				<el-table-column type="selection" ></el-table-column>
				<el-table-column type="expand" >
					<template #default="scope">
						<div style="text-align: center">
							<el-card style=" color: #333">
								<el-row>
									<el-col :span="6">
										<el-avatar :size="100" :src=scope.row.avatar ></el-avatar>
									</el-col>
									<el-col :span="18">
										<el-tag type="success" disable-transitions>
											Phone:{{scope.row.phone}}
										</el-tag>
										<br/>
										<el-tag type="success" disable-transitions>
											Email:{{scope.row.email}}
										</el-tag>
									</el-col>
								</el-row>
							</el-card>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="name" label="用户名">
					<template #default="scope">
						<div style="text-align: center" >
							<el-tag type="success" disable-transitions>
								{{scope.row.name}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="phone" label="用户电话">
					<template #default="scope">
						<div>
							<el-tag type="info" disable-transitions>
								{{scope.row.phone}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="sex" label="用户性别">
					<template #default="scope">
						<div style="text-align: center">
							<el-tag type="danger" disable-transitions>
								{{scope.row.sex}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="age" label="用户年龄">
					<template #default="scope">
						<div style="text-align: center">
							<el-tag type="info" disable-transitions>
								{{scope.row.age}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="userType" label="用户类型"  >
					<template #default="scope">
						<div style="text-align: center">
							<el-tag type="warning" disable-transitions>
								{{scope.row.userType}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="credit" label="用户积分"  >
					<template #default="scope">
						<div style="text-align: center">
							<el-tag type="warning" disable-transitions>
								{{scope.row.credit}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="status" label="账户状态"  >
					<template #default="scope">
						<div style="text-align: center">
							<el-tag type="warning" disable-transitions>
								{{scope.row.status}}
							</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column   label="Operations" width="200rem">
					<template #header>
						<el-dropdown>
							<p style="font-size: 25%">
								<el-button size="small" v-model="searchKey" >
									<el-icon><Search /></el-icon>
									{{searchKey}}
								</el-button>
							</p>
							<template #dropdown>
								<el-dropdown-menu>
									<el-dropdown-item @click="searchNone">None</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'name'; searchValue=''">Name</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'sex'; searchValue='';">Sex</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'age';searchValue2.val1='';searchValue2.val2='';">Age</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'email'; searchValue='';">Email</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'phone'; searchValue='';">Phone</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'status'; searchValue='';">Status</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'created';searchValue2.val1='';searchValue2.val2='';">createdTime</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'credit';searchValue2.val1='';searchValue2.val2='';">credit</el-dropdown-item>
									<el-dropdown-item @click="searchKey = 'userType'; searchValue='';">userType</el-dropdown-item>
								</el-dropdown-menu>
							</template>
						</el-dropdown>
						<el-button style="margin-top: 0.15rem;" size="small" @click="search">搜索确认</el-button>

						<el-select v-if="searchKey === 'sex'" v-model="searchValue" class="m-2" placeholder="Select" size="small">
								<el-option style="font-size: small" @click="search" value="MAN">MAN</el-option>
								<el-option style="font-size: small" @click="search" value="WOMAN">WOMAN</el-option>
							</el-select>
							<el-select v-else-if="searchKey === 'userType'" v-model="searchValue" class="m-2" placeholder="Select" size="small">
								<el-option style="font-size: small" @click="search" value="USER">USER</el-option>
								<el-option style="font-size: small" @click="search" value="VIPUSER">VIPUSER</el-option>
							</el-select>

							<el-select v-else-if="searchKey === 'status'" v-model="searchValue" class="m-2" placeholder="Select" size="small">
								<el-option style="font-size: small" @click="search" value="OK">OK</el-option>
								<el-option style="font-size: small" @click="search" value="LOCKED">LOCKED</el-option>
								<el-option style="font-size: small" @click="search" value="DELETED">DELETED</el-option>
							</el-select>
							<div v-else-if="searchKey ==='created'">
								<el-date-picker v-model="searchValue2.val1" type="datetime" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss" style="width: 7.5rem;" placeholder="begin" size="small"></el-date-picker>
								<el-date-picker v-model="searchValue2.val2" type="datetime" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss" style="width: 7.5rem;" placeholder="end" size="small"></el-date-picker>
							</div>
							<div v-else-if="searchKey ==='credit'||searchKey ==='age'">
								<el-input v-model="searchValue2.val1" style="width: 4rem;" placeholder="min" @keyup.enter.native="search" size="small"></el-input>
								<el-input v-model="searchValue2.val2" style="width: 4rem;" placeholder="max" @keyup.enter.native="search" size="small"></el-input>
							</div>

							<el-input v-else v-model="searchValue" size="small"
												 @keyup.enter.native="search">
							</el-input>
					</template>
					<template #default="{ row }">
						<el-button type="danger" size="small" @click="del(row)">Delete</el-button>
						<el-button type="primary" size="small" @click="showDialog('edit',row)">Edit</el-button>
					</template>
				</el-table-column>
			</el-table>

			<el-dialog v-model="visible" @close="hideDialog">
				<template v-if="dialogCategory=='add'">
					<div style="text-align: center">
						<h1 slot="title">Add User</h1>
					</div>
					<Add  @updateTable="updateTable" @cancel="hideDialog" @close="hideDialog" ></Add>
				</template>
				<template v-else-if="dialogCategory=='edit'">
					<div style="text-align: center">
						<h1 slot="title">Edit User</h1>
					</div>
					<Edit :rowData="rowData" :rowIndex="rowIndex" @updateTable="updateTable" @cancel="hideDialog" @close="hideDialog"></Edit>
				</template>
				<template v-else-if="dialogCategory=='updatelist'">
					<div style="text-align: center">
						<h1 slot="title">Edit UserList</h1>
					</div>
					<EditList :multipleSelection="multipleSelection" @updateTable="updateTable" @cancel="hideDialog" @close="hideDialog" ></EditList>
				</template>
				<template v-else-if="dialogCategory=='searchlist'">
					<div style="text-align: center">
						<h1 slot="title">Search UserList</h1>
					</div>
					<SearchList @updateSearchlistTable="updateSearchlistTable" :searchlist-value="searchlistValue"  @updateTable="updateTable" @cancel="hideDialog" @close="hideDialog" ></SearchList>
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
	import axios from "axios";
	import { ref } from 'vue'
	import Add from './add'
	import Edit from './edit'
	import EditList from './editList'
	import SearchList from './searchList'
	import { ElMessage, ElMessageBox } from 'element-plus'
	import ExportJsonExcel from 'js-export-excel';
	export default{
		components:{
			Add,
			Edit,
			EditList,
			SearchList
		},
		data(){
			return{
				tableData: [], // 表格数据
				multipleSelection: [], // 选择后的数据
				currentPage: 1,
				total: 0,
				pageSize: 1,
				visible:ref(false),
				rowData:[],
				rowIndex:-1,
				dialogCategory:'',
				searchKey:'None',
				searchValue:'',
				searchValue2:{val1:'',val2:''},
				isSearch:false,
				searchlistValue:{
					name:'',
					sex:'',
					age:'',
					age2:'',
					email:'',
					phone:'',
					created:'',
					created2:'',
					credit:'',
					credit2:'',
					status:'',
					userType:'',
					isOK:false,
					isDeleted:false,
					isLocked:false,
					isMan:false,
					isWoman:false,
					isNotVIP:false,
					isVIP:false
				},
			}
		},
		created() {
			this.page(1)
		},
		methods:{
			//上传
			importfxx(obj) {
				// 通过DOM取文件数据
				var f = event.currentTarget.files[0];
				var rABS = false; //是否将文件读取为二进制字符串
				var reader = new FileReader();
				//if (!FileReader.prototype.readAsBinaryString) {
				FileReader.prototype.readAsBinaryString = function(f) {
					var binary = "";
					var rABS = false; //是否将文件读取为二进制字符串
					var wb; //读取完成的数据
					var outdata;
					var reader = new FileReader();
					reader.onload = function(e) {
						var bytes = new Uint8Array(reader.result);
						var length = bytes.byteLength;
						for (var i = 0; i < length; i++) {
							binary += String.fromCharCode(bytes[i]);
						}
						//此处引入，用于解析excel
						var XLSX = require("xlsx");
						wb = XLSX.read(binary, {
							type: "binary"
						});
						outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
						console.log("outdata")
						console.log(outdata)
						ElMessageBox.confirm(
							"确定批量添加吗？",
							'warning',
							{
								confirmButtonText: 'OK',
								cancelButtonText: 'Cancel',
								type: 'warning',
							}
						).then(()=>{
							axios.post('user/addlist',outdata).then(res=>{
								console.log("res2")
								console.log(res);
								if(res.data.code===200){
									ElMessage({
										type: 'success',
										message: '批量上传成功',
									})
								}else{
									ElMessage({
										type:'warning',
										message:'批量上传失败'
									});
								}
							})
							console.log("outdata2")
							console.log(outdata)
						})
					};
					reader.readAsArrayBuffer(f);

				};

				if (rABS) {
					reader.readAsArrayBuffer(f);
				} else {
					reader.readAsBinaryString(f);
					console.log('return?')
				}
			},
			//上传文件时处理方法
			handleChange(file, fileList){
				this.fileTemp = file.raw;
				if(this.fileTemp){
					if((this.fileTemp.type == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
						|| (this.fileTemp.type == 'application/vnd.ms-excel')){
						this.importfxx(this.fileTemp);
						console.log('niuniu')
						this.page(this.currentPage)
					} else {
						this.$message({
							type:'warning',
							message:'附件格式错误，请删除后重新上传！'
						})
					}
				} else {
					this.$message({
						type:'warning',
						message:'请上传附件！'
					})
				}
			},
			//超出最大上传文件数量时的处理方法
			handleExceed(){
				this.$message({
					type:'warning',
					message:'超出最大上传文件数量的限制！'
				})
				return;
			},
			//移除文件的操作方法
			handleRemove(file,fileList){
				this.fileTemp = null
			},
			//下载
			exportJsonExcel(data){
				const  dataSource  = data;
				//3. 定义表头，数据
				let option = {}, tableData = [], tableTitle;
				tableTitle = ['id', 'uuid', 'name', 'avatar', 'email','phone', 'password', 'status', 'created',
					'sex', 'age', 'accountType', 'userType', 'credit'];
				console.log(dataSource)
				if(!dataSource || dataSource.length === 0){
					this.$message({
						type:'warning',
						message:'没有可供导出的信息'
					});
					return;
				}
				dataSource.map((item) => {
					let objRow = {
						'id': item.id,
						'uuid': item.uuid,
						'name': item.name,
						'avatar': item.avatar,
						'email': item.email,
						'password': item.password,
						'status': item.status,
						'created': item.created,
						'phone': item.phone,
						'sex': item.sex,
						'age': item.age,
						'accountType': item.accountType,
						'userType': item.userType,
						'credit': item.credit,
					};
					tableData.push(objRow);
				});

				//4.下载的表格文件名
				option.fileName = '用户信息' + (new Date()).getTime();

				option.datas = [
					{
						sheetName: 'Report',      //名字(可有可无)(默认 sheet1)
						sheetData: tableData,     //数据源
						sheetHeader: tableTitle,  //表头数据
						sheetFilter: tableTitle,  //表头数据对应的sheetData数据
					}
				];

				let toExcel = new ExportJsonExcel(option);
				toExcel.saveExcel();
			},
			searchNone(){
				console.log("searchNone")
				this.isSearch=false
				this.searchKey='None'
				this.searchValue=''
				this.searchValue2.val1=''
				this.searchValue2.val2=''
				this.page(1)
			},
			search(){
				this.isSearch=true
				if(this.searchKey==''){
					this.$message({
						type:'warning',
						message:'你尚未选择列名'
					});
					return;
				}
				switch (this.searchKey) {
					case "searchlist":
						this.getTableData('/user/searchlist',{searchlist:this.searchlistValue,currentPage:this.currentPage});
						break;
					case "name":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,name:this.searchValue,currentPage:this.currentPage});
						break;
					case "email":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,email:this.searchValue,currentPage:this.currentPage});
						break;
					case "status":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,status:this.searchValue,currentPage:this.currentPage});
						break;
					case "userType":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,userType:this.searchValue,currentPage:this.currentPage});
						break;
					case "age":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,age:this.searchValue2.val1,age2:this.searchValue2.val2,currentPage:this.currentPage});
						break;
					case "credit":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,credit:this.searchValue2.val1,credit2:this.searchValue2.val2,currentPage:this.currentPage})
						break;
					case "created":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,created:this.searchValue2.val1,created2:this.searchValue2.val2,currentPage:this.currentPage});
						break;
					case "phone":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,phone:this.searchValue,currentPage:this.currentPage});
						break;
					case "sex":
						this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,sex:this.searchValue,currentPage:this.currentPage});
						break;
				}

			},
			del(val) {
				axios.post('/user/del',val).then(res=>{
					if(res.data.code===200){
						this.page(this.currentPage)
						this.$message({
							type:'success',
							message:'删除成功'
						});
					}else{
						this.$message({
							type:'warning',
							message:'删除失败'
						});
					}
				});

			},
			delList(){
				ElMessageBox.confirm(
					'确认批量删除吗？',
					'Warning',
					{
						confirmButtonText: 'OK',
						cancelButtonText: 'Cancel',
						type: 'warning',
					}
				)
					.then(() => {
						console.log('ooooo')
						if(this.multipleSelection.length===0){
							ElMessage({
								type: 'info',
								message: '您尚未选择删除条目',
							})
							return;
						}
						axios.post('user/dellist',this.multipleSelection).then(res=>{
							if(res.data.code==200){
								this.page(this.currentPage)
								ElMessage({
									type: 'success',
									message: '批量删除成功',
								})
								this.page(this.currentPage)
							}else{
								ElMessage({
									type: 'warning',
									message: '网络异常',
								})
							}
						})
					})
			},
			page(currentPage) {
				if(this.isSearch){
					switch (this.searchKey) {
						case "searchlist":
							this.getTableData('/user/searchlist',{searchlist:this.searchlistValue,currentPage:currentPage});
							break;
						case "name":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,name:this.searchValue,currentPage:currentPage});
							break;
						case "email":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,email:this.searchValue,currentPage:currentPage});
							break;
						case "status":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,status:this.searchValue,currentPage:currentPage});
							break;
						case "userType":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,userType:this.searchValue,currentPage:currentPage});
							break;
						case "age":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,age:this.searchValue2.val1,age2:this.searchValue2.val2,currentPage:currentPage});
							break;
						case "credit":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,credit:this.searchValue2.val1,credit2:this.searchValue2.val2,currentPage:currentPage});
							break;
						case "created":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,created:this.searchValue2.val1,created2:this.searchValue2.val2,currentPage:currentPage});
							break;
						case "phone":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,phone:this.searchValue,currentPage:currentPage});
							break;
						case "sex":
							this.getTableData('/user/searchbycondition',{searchKey:this.searchKey,sex:this.searchValue,currentPage:currentPage});
							break;
					}
				}else{
					console.log('NIU')
					axios.get("/user/users?currentPage=" + currentPage).then(res => {
						// console.log(res.data.data.records)
						this.tableData = res.data.data.records
						this.currentPage = res.data.data.current
						this.total = res.data.data.total
						this.pageSize = res.data.data.size
					})
				}
			},
			getTableData(url,data){
				axios.post(url,data).then(res=>{
					this.tableData = res.data.data.records
					this.currentPage = res.data.data.current
					this.total = res.data.data.total
					this.pageSize = res.data.data.size
				})
			},
			// 显示弹窗
			showDialog(dialogCategory = '',val) {
				if(dialogCategory=='edit'){//编辑
					// console.log('val')
					// console.log(val)
					// console.log('editRow')
					this.rowData=val;
					// console.log("this.rowData")
					// console.log(this.rowData)
					for(var i=0,len=this.tableData.length;i<len;i++){
						if(this.tableData[i]===this.rowData){
							this.rowIndex=i;
							break;
						}
					}
				}else if(dialogCategory=='updatelist'){//添加
					if(this.multipleSelection.length===0){
						this.$message({
							type:'warning',
							message:'你尚未选择条目'
						});
						return 0;
					}
				}else if(dialogCategory=='searchlist'){
					console.log("dialogCategory=='searchlist'")
					this.searchKey='searchlist'
				}
				this.visible=true;
				this.dialogCategory = dialogCategory;
			},
			updateTable(){
				this.page(this.currentPage)
				this.visible=false;
				this.dialogCategory='';
			},
			updateSearchlistTable(){
				this.isSearch=true;
				this.visible=false;
				this.dialogCategory='';
				// console.log('hohoho')
				this.page(this.currentPage)

			},
			// 关闭弹窗
			hideDialog() {
				this.visible=false;
				this.dialogCategory='';
			},

			/**
			 * el-table点击选择操作
			 */
			handleSelectionChange(val){
				this.multipleSelection = val;
				console.log(this.multipleSelection)
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
