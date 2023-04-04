<template>
	<div>
		<el-form :model="form"  label-width="auto">
			<el-form-item label="Name" prop="name">
				<el-input v-model="form.name" />
			</el-form-item>
			<el-form-item label="Sex" prop="sex">
				<el-checkbox v-model="form.isMan" >
					<el-tag type="success" disable-transitions>
						MAN
					</el-tag>
				</el-checkbox>
				<el-checkbox v-model="form.isWoman" >
					<el-tag type="success" disable-transitions>
						WOMAN
					</el-tag>
				</el-checkbox>
			</el-form-item>
			<el-form-item label="Status" prop="status">
					<el-checkbox v-model="form.isOK" >
						<el-tag type="success" disable-transitions>
							OK
						</el-tag>
					</el-checkbox>
					<el-checkbox v-model="form.isLocked" >
						<el-tag type="success" disable-transitions>
							LOCKED
						</el-tag>
					</el-checkbox>
					<el-checkbox v-model="form.isDeleted" >
						<el-tag type="success" disable-transitions>
							DELETED
						</el-tag>
					</el-checkbox>
			</el-form-item>
			<el-form-item label="userType" prop="userType">
				<el-checkbox v-model="form.isNotVIP" >
					<el-tag type="success" disable-transitions>
						USER
					</el-tag>
				</el-checkbox>
				<el-checkbox v-model="form.isVIP"  >
					<el-tag type="success" disable-transitions>
						VIPUSER
					</el-tag>
				</el-checkbox>
			</el-form-item>
			<el-form-item label="Created" prop="created">
				<el-date-picker v-model="form.created" type="datetime" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"  style="width: 40%;" placeholder="min"/>
				<el-date-picker v-model="form.created2" type="datetime" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss" style="width: 40%;margin-left: 2rem;" placeholder="max" />
			</el-form-item>
			<el-form-item label="Credit" prop="credit">
				<el-input v-model="form.credit"   style="width: 40%;" placeholder="min"/>
				<el-input v-model="form.credit2"  style="width: 40%;margin-left: 2rem;" placeholder="max" />
			</el-form-item>
			<el-form-item label="Age" prop="age">
				<el-input v-model="form.age" style="width: 40%;" placeholder="min"/>
				<el-input v-model="form.age2" style="width: 40%;margin-left: 2rem;" placeholder="max" />
			</el-form-item>
			<el-form-item label="Email" prop="email">
				<el-input v-model="form.email" />
			</el-form-item>

			<el-form-item label="Password" prop="password">
				<el-input v-model="form.password" >
				</el-input>
			</el-form-item>
			<el-form-item label="Phone" prop="phone">
				<el-input v-model="form.phone">
				</el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onsubmit('form')">Submit</el-button>
				<el-button @click="close(form)">Cancel</el-button>
			</el-form-item>
		</el-form>
	</div>

</template>

<script>
	import { ElMessageBox } from 'element-plus'
	import { ref} from 'vue'
	import axios from 'axios'
	export default {
		name: "searchList.vue",
		props:["searchlistValue"],
		data(){
			return{
				form :{
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
				rules: {
				},
			}
		},
		methods:{
			onsubmit(form){
				console.log("this.form")
				console.log(this.form)
				this.searchlistValue.name=this.form.name;
				this.searchlistValue.sex=this.form.sex;
				this.searchlistValue.age=this.form.age;
				this.searchlistValue.age2=this.form.age2;
				this.searchlistValue.email=this.form.email;
				this.searchlistValue.phone=this.form.phone;
				this.searchlistValue.created=this.form.created;
				this.searchlistValue.created2=this.form.created2;
				this.searchlistValue.credit=this.form.credit;
				this.searchlistValue.credit2=this.form.credit2;
				this.searchlistValue.status=this.form.status;
				this.searchlistValue.userType=this.form.userType;
				this.searchlistValue.isOK=this.form.isOK;
				this.searchlistValue.isDeleted=this.form.isDeleted;
				this.searchlistValue.isLocked=this.form.isLocked;
				this.searchlistValue.isMan=this.form.isMan;
				this.searchlistValue.isWoman=this.form.isWoman;
				this.searchlistValue.isNotVIP=this.form.isNotVIP;
				this.searchlistValue.isVIP=this.form.isVIP;

				axios.post('/user/searchlist', {searchlist:this.form,currentPage:1}).then(res=>{
					console.log(res)
					if(res.data.code===200){
						this.$message({
							type:'success',
							message:'批量更新成功'
						});
						console.log('searchlist--emit--search')
						this.$emit('updateSearchlistTable')
					}else{
						this.$message({
							type:'warning',
							message:'批量更新失败'
						});
					}
				})
			},
			close(form){
				this.$emit('hideDialog',form);
			},
		},
	}
</script>

<style scoped>

</style>