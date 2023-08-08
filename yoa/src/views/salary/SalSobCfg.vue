<!--<template>-->
<!--    <div>-->
<!--        <el-table :data="emps"-->
<!--                  border-->
<!--                  size="mini"-->
<!--                  stripe>-->
<!--            <el-table-column-->
<!--                    type="selection"-->
<!--                    align="left"-->
<!--                    width="55">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    prop="name"-->
<!--                    label="姓名"-->
<!--                    align="left"-->
<!--                    fixed-->
<!--                    width="120">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    prop="workID"-->
<!--                    label="工号"-->
<!--                    align="left"-->
<!--                    width="120">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    prop="email"-->
<!--                    label="电子邮箱"-->
<!--                    align="left"-->
<!--                    width="200">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    prop="phone"-->
<!--                    label="电话号码"-->
<!--                    align="left"-->
<!--                    width="120">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    prop="department.name"-->
<!--                    label="所属部门"-->
<!--                    align="left"-->
<!--                    width="120">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    label="工资账套"-->
<!--                    align="center">-->
<!--                <template slot-scope="scope">-->
<!--                    <el-tooltip placement="right" v-if="scope.row.salary">-->
<!--                        <div slot="content">-->
<!--                            <table>-->
<!--                                <tr>-->
<!--                                    <td>基本工资</td>-->
<!--                                    <td>{{scope.row.salary.basicSalary}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>交通补助</td>-->
<!--                                    <td>{{scope.row.salary.trafficSalary}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>午餐补助</td>-->
<!--                                    <td>{{scope.row.salary.lunchSalary}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>奖金</td>-->
<!--                                    <td>{{scope.row.salary.bonus}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>养老金比率</td>-->
<!--                                    <td>{{scope.row.salary.pensionPer}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>养老金基数</td>-->
<!--                                    <td>{{scope.row.salary.pensionBase}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>医疗保险比率</td>-->
<!--                                    <td>{{scope.row.salary.medicalPer}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>医疗保险基数</td>-->
<!--                                    <td>{{scope.row.salary.medicalBase}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>公积金比率</td>-->
<!--                                    <td>{{scope.row.salary.accumulationFundPer}}</td>-->
<!--                                </tr>-->
<!--                                <tr>-->
<!--                                    <td>公积金基数</td>-->
<!--                                    <td>{{scope.row.salary.accumulationFundBase}}</td>-->
<!--                                </tr>-->
<!--                            </table>-->
<!--                        </div>-->
<!--                        <el-tag>{{scope.row.salary.name}}</el-tag>-->
<!--                    </el-tooltip>-->
<!--                    <el-tag v-else>暂未设置</el-tag>-->
<!--                </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    label="操作"-->
<!--                    align="center">-->
<!--                <template slot-scope="scope">-->
<!--                    <el-popover-->
<!--                            placement="left"-->
<!--                            title="编辑工资账套"-->
<!--                            @show="showPop(scope.row.salary)"-->
<!--                            @hide="hidePop(scope.row)"-->
<!--                            width="200"-->
<!--                            trigger="click">-->
<!--                        <div>-->
<!--                            <el-select v-model="currentSalary" size="mini" placeholder="请选择">-->
<!--                                <el-option-->
<!--                                        v-for="item in salaries"-->
<!--                                        :key="item.id"-->
<!--                                        :label="item.name"-->
<!--                                        :value="item.id">-->
<!--                                </el-option>-->
<!--                            </el-select>-->
<!--                        </div>-->
<!--                        <el-button slot="reference" type="danger">修改工资账套</el-button>-->
<!--                    </el-popover>-->
<!--                </template>-->
<!--            </el-table-column>-->
<!--        </el-table>-->
<!--        <div style="display: flex;justify-content: flex-end;margin-top: 10px">-->
<!--            <el-pagination-->
<!--                    background-->
<!--                    @current-change="currentChange"-->
<!--                    @size-change="sizeChange"-->
<!--                    layout="total, sizes, prev, pager, next, jumper, slot"-->
<!--                    :total="total">-->
<!--            </el-pagination>-->
<!--        </div>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->
<!--    export default {-->
<!--        name: "SalSobCfg",-->
<!--        data() {-->
<!--            return {-->
<!--                emps: [],-->
<!--                salaries: [],-->
<!--                currentPage: 1,-->
<!--                currentSalary:null,-->
<!--                size: 10,-->
<!--                total: 0-->
<!--            }-->
<!--        },-->
<!--        mounted() {-->
<!--            this.initEmps();-->
<!--            this.initSalaries();-->
<!--        },-->
<!--        methods: {-->
<!--            showPop(data){-->
<!--                if(data){-->
<!--                    this.currentSalary = data.id;-->
<!--                }else{-->
<!--                    this.currentSalary = null;-->
<!--                }-->
<!--            },-->
<!--            hidePop(data){-->
<!--                if(this.currentSalary && this.currentSalary!=data.salary.id){-->
<!--                    this.putRequest('/salary/sobcfg/?eid='+data.id+'&sid='+this.currentSalary).then(resp=>{-->
<!--                        if(resp){-->
<!--                            this.initEmps();-->
<!--                        }-->
<!--                    })-->
<!--                }-->
<!--            },-->
<!--            initSalaries() {-->
<!--                this.getRequest('/salary/sobcfg/salaries').then(resp => {-->
<!--                    if (resp) {-->
<!--                        this.salaries = resp;-->
<!--                    }-->
<!--                })-->
<!--            },-->
<!--            currentChange(page) {-->
<!--                this.currentPage = page;-->
<!--                this.initEmps();-->
<!--            },-->
<!--            sizeChange(size) {-->
<!--                this.size = size;-->
<!--                this.initEmps();-->
<!--            },-->
<!--            initEmps() {-->
<!--                this.getRequest('/salary/sobcfg/?currentPage=' + this.currentPage + '&size=' + this.size).then(resp => {-->
<!--                    if (resp) {-->
<!--                        this.emps = resp.data;-->
<!--                        this.total = resp.total;-->
<!--                    }-->
<!--                })-->
<!--            }-->
<!--        }-->
<!--    }-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
