<template>
    <div>
        <el-timeline >
            <el-timeline-item center
                              :type="primary"
                              :hollow=true
                              :timestamp="notice.created" placement="top" v-for="notice in notices">
                <el-card>
                    <router-link :to="{name: 'NoticeDetail', params: {noticeId: notice.id}}">
                        {{notice.title}}
                    </router-link>
                    <p>{{notice.description}}</p>
                </el-card>
            </el-timeline-item>
        </el-timeline>
        <div style="text-align: center">
            <el-pagination class="mpage" layout="prev, pager, next"
                           :current-page="currentPage"
                           :page-size="pageSize"
                           :total="total"
                           @current-change=page
            />
        </div>

    </div>
</template>

<script>
    import Header from '../../components/header'
    import axios from 'axios'
    export default {
        name: "Notices",
        data() {
            return {
                notices: {},
                currentPage: 1,
                total: 0,
                pageSize: 1
            }
        },
        methods:{
            page(currentPage) {

                axios.get("/notices?currentPage=" + currentPage).then(res => {
                    // console.log(res)
                    this.notices = res.data.data.records
                    this.currentPage = res.data.data.current
                    this.total = res.data.data.total
                    this.pageSize = res.data.data.size

                })
            }
        },
        created() {
            this.page(1)
        }
    }
</script>

<style scoped>
    .mpage {
        margin: 0 auto;
        align: center;
    }
</style>
