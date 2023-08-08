<!--<template xmlns:el-col="http://www.w3.org/1999/html">-->
<!--&lt;!&ndash;  路由中的：基本资料界面&ndash;&gt;-->
<!--    <div>-->
<!--        <div>-->
<!--            <div style="display: flex;justify-content: space-between">-->
<!--                <div>-->
<!--                    <el-input style="width: 300px;margin-right: 10px"-->
<!--                              prefix-icon="el-icon-search"-->
<!--                              v-model="empName"-->
<!--                              @keydown.enter.native="initEmps"-->
<!--                              clearable-->
<!--                              @clear="initEmps"-->
<!--                              :disabled="showAdvanceSearchVisible"-->
<!--                              placeholder="请输入员工名进行搜索..."></el-input>-->
<!--                    <el-button type="primary" icon="el-icon-search" @click="initEmps"-->
<!--                               :disabled="showAdvanceSearchVisible">搜索-->
<!--                    </el-button>-->
<!--                    <el-button type="primary" @click="showAdvanceSearchVisible=!showAdvanceSearchVisible">-->
<!--                        高级搜索-->
<!--                        <i :class="showAdvanceSearchVisible?'fa fa-angle-double-up':'fa fa-angle-double-down'"-->
<!--                           aria-hidden="true"></i>-->
<!--                    </el-button>-->
<!--                </div>-->
<!--                <div>-->
<!--                    <el-upload-->
<!--                            style="display: inline-flex;margin-right: 8px"-->
<!--                            :headers="headers"-->
<!--                            :show-file-list="false"-->
<!--                            :before-upload="beforeUpload"-->
<!--                            :on-success="onSuccess"-->
<!--                            :on-error="onError"-->
<!--                            :disabled="importDataDisabled"-->
<!--                            action="/employee/basic/import">-->
<!--                        <el-button type="success" :icon="importDataIcon" :disabled="importDataDisabled">-->
<!--                            {{importDataBtnText}}-->
<!--                        </el-button>-->
<!--                    </el-upload>-->
<!--                    <el-button @click="exportData" type="success" icon="el-icon-download">-->
<!--                        导出数据-->
<!--                    </el-button>-->
<!--                    <el-button type="primary" icon="el-icon-plus" @click="showAddEmpView">添加员工</el-button>-->
<!--                </div>-->
<!--            </div>-->
<!--        </div>-->
<!--        <transition name="slide-fade">-->
<!--            <div v-show="showAdvanceSearchVisible" style="border: 1px solid #409eff;border-radius: 5px;-->
<!--             box-sizing: border-box;padding: 5px;margin: 10px 0px">-->
<!--                <el-row style="margin-left: 15px">-->
<!--                    <el-col :span="5">-->
<!--                        政治面貌:-->
<!--                        <el-select v-model="searchValue.politicId" placeholder="政治面貌" size="mini" style="width: 140px;">-->
<!--                            <el-option-->
<!--                                    v-for="item in politicsstatus"-->
<!--                                    :key="item.id"-->
<!--                                    :label="item.name"-->
<!--                                    :value="item.id">-->
<!--                            </el-option>-->
<!--                        </el-select>-->
<!--                    </el-col>-->
<!--                    <el-col :span="4">-->
<!--                        民族:-->
<!--                        <el-select v-model="searchValue.nationId" placeholder="民族" size="mini" style="width: 130px">-->
<!--                            <el-option v-for="item in nations"-->
<!--                                       :key="item.id"-->
<!--                                       :label="item.name"-->
<!--                                       :value="item.id">-->
<!--                            </el-option>-->
<!--                        </el-select>-->
<!--                    </el-col>-->
<!--                    <el-col :span="4">-->
<!--                        职位:-->
<!--                        <el-select v-model="searchValue.posId" placeholder="请选择职位" size="mini" style="width: 130px">-->
<!--                            <el-option v-for="item in positions"-->
<!--                                       :key="item.id"-->
<!--                                       :label="item.name"-->
<!--                                       :value="item.id">-->
<!--                            </el-option>-->
<!--                        </el-select>-->
<!--                    </el-col>-->
<!--                    <el-col :span="4">-->
<!--                        职称:-->
<!--                        <el-select v-model="searchValue.jobLevelId" placeholder="请选择职称" size="mini"-->
<!--                                   style="width: 130px">-->
<!--                            <el-option v-for="item in joblevels"-->
<!--                                       :key="item.id"-->
<!--                                       :label="item.name"-->
<!--                                       :value="item.id">-->
<!--                            </el-option>-->
<!--                        </el-select>-->
<!--                    </el-col>-->
<!--                    <el-col :span="7">-->
<!--                        聘用形式:-->
<!--                        <el-radio-group v-model="searchValue.engageForm">-->
<!--                            <el-radio label="劳动合同"></el-radio>-->
<!--                            <el-radio label="劳务合同"></el-radio>-->
<!--                        </el-radio-group>-->
<!--                    </el-col>-->
<!--                </el-row>-->
<!--                <el-row style="margin-top: 10px;margin-left: 15px">-->
<!--                    <el-col :span="5">-->
<!--                        所属部门:-->
<!--                        <el-popover-->
<!--                                placement="bottom"-->
<!--                                title="请选择部门"-->
<!--                                width="200"-->
<!--                                trigger="manual"-->
<!--                                v-model="visible2">-->
<!--                            <el-tree default-expand-all-->
<!--                                     :data="allDeps"-->
<!--                                     :props="defaultProps"-->
<!--                                     @node-click="searchHandleNodeClick"-->
<!--                                     style="width: 210px">-->
<!--                            </el-tree>-->
<!--                            <div slot="reference"-->
<!--                                 style="width: 130px; display: inline-flex; border: 1px solid #dedede;-->
<!--                                                height: 26px; border-radius:5px; cursor: pointer;align-items: center;-->
<!--                                                font-size: 12px;padding-left: 8px;box-sizing: border-box"-->
<!--                                 @click="showDepView2">-->
<!--                                {{inputDepName}}-->
<!--                            </div>-->
<!--                        </el-popover>-->
<!--                    </el-col>-->
<!--                    <el-col :span="10">-->
<!--                        入职日期:-->
<!--                        <el-date-picker-->
<!--                                v-model="searchValue.beginDateScope"-->
<!--                                size="mini"-->
<!--                                type="daterange"-->
<!--                                value-format="yyyy-MM-dd"-->
<!--                                unlink-panels-->
<!--                                range-separator="至"-->
<!--                                start-placeholder="开始日期"-->
<!--                                end-placeholder="结束日期">-->
<!--                        </el-date-picker>-->
<!--                    </el-col>-->
<!--                    <el-col :span="4" :offset="5">-->
<!--                        <el-button size="mini">取消</el-button>-->
<!--                        <el-button @click="initEmps('advanced')" size="mini" icon="el-icon-search" type="primary">搜索-->
<!--                        </el-button>-->
<!--                    </el-col>-->
<!--                </el-row>-->
<!--            </div>-->
<!--        </transition>-->
<!--        <div style="margin-top: 10px">-->
<!--            <el-table-->
<!--                    :data="emps"-->
<!--                    stripe-->
<!--                    border-->
<!--                    v-loading="loading"-->
<!--                    element-loading-text="拼命加载中"-->
<!--                    element-loading-spinner="el-icon-loading"-->
<!--                    element-loading-background="rgba(0, 0, 0, 0.8)"-->
<!--                    style="width: 100%">-->
<!--                <el-table-column-->
<!--                        type="selection"-->
<!--                        width="55">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="name"-->
<!--                        label="姓名"-->
<!--                        fixed-->
<!--                        width="80px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="workID"-->
<!--                        label="工号"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="gender"-->
<!--                        label="性别"-->
<!--                        width="45px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="birthday"-->
<!--                        label="出生日期"-->
<!--                        width="90px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="idCard"-->
<!--                        label="身份证号"-->
<!--                        width="150px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="wedlock"-->
<!--                        label="婚姻状况"-->
<!--                        width="60px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="nation.name"-->
<!--                        label="民族"-->
<!--                        width="60px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="nativePlace"-->
<!--                        label="籍贯"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="politicsStatus.name"-->
<!--                        label="政治面貌"-->
<!--                        width="130px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="email"-->
<!--                        label="邮箱"-->
<!--                        width="170px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="phone"-->
<!--                        label="电话号码"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="address"-->
<!--                        label="联系地址"-->
<!--                        width="270px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="department.name"-->
<!--                        label="所属部门"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="position.name"-->
<!--                        label="职位"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="joblevel.name"-->
<!--                        label="职称"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="engageForm"-->
<!--                        label="聘用形式"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="tiptopDegree"-->
<!--                        label="最高学历"-->
<!--                        width="80px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="school"-->
<!--                        label="毕业院校"-->
<!--                        width="150px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="specialty"-->
<!--                        label="专业"-->
<!--                        width="150px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="workState"-->
<!--                        label="在职状态"-->
<!--                        width="80px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="beginDate"-->
<!--                        label="入职日期"-->
<!--                        width="120px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="conversionTime"-->
<!--                        label="转正日期"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="beginContract"-->
<!--                        label="合同起始日期"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="endContract"-->
<!--                        label="合同截止日期"-->
<!--                        width="100px"-->
<!--                        align="center">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        label="合同期限"-->
<!--                        width="120px"-->
<!--                        align="center">-->
<!--                    <template slot-scope="scope">-->
<!--                        <el-tag>{{scope.row.contractTerm}}</el-tag>-->
<!--                        年-->
<!--                    </template>-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        label="操作"-->
<!--                        align="center"-->
<!--                        fixed="right"-->
<!--                        width="200px">-->
<!--                    <template slot-scope="scope">-->
<!--                        <el-button @click="showEditEmpView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>-->
<!--                        <el-button style="padding: 3px" size="mini" type="primary">查看高级资料</el-button>-->
<!--                        <el-button @click="deleteEmp(scope.row)" style="padding: 3px" size="mini" type="danger">删除-->
<!--                        </el-button>-->
<!--                    </template>-->
<!--                </el-table-column>-->
<!--            </el-table>-->
<!--            <div style="display: flex;justify-content: flex-end;margin-top: 8px">-->
<!--                <el-pagination-->
<!--                        background-->
<!--                        @current-change="currentChange"-->
<!--                        @size-change="sizeChange"-->
<!--                        layout=" total, sizes, prev, pager, next, jumper"-->
<!--                        :total="total">-->
<!--                </el-pagination>-->
<!--            </div>-->
<!--        </div>-->
<!--        <el-dialog-->
<!--                :title="title"-->
<!--                :visible.sync="dialogVisible"-->
<!--                width="80%">-->
<!--            <div>-->
<!--                <el-form ref="empForm" :model="emp" :rules="rules">-->
<!--                    <el-row>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="姓名" prop="name">-->
<!--                                <el-input size="mini" style="width: 180px" v-model="emp.name" prefix-icon="el-icon-user"-->
<!--                                          placeholder="请输入员工姓名"></el-input>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="5">-->
<!--                            <el-form-item label="性别：" prop="gender">-->
<!--                                <el-radio-group v-model="emp.gender" style="margin-top: 8px">-->
<!--                                    <el-radio label="男"></el-radio>-->
<!--                                    <el-radio label="女"></el-radio>-->
<!--                                </el-radio-group>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="出生日期" prop="birthday">-->
<!--                                <el-date-picker type="date"-->
<!--                                                value-format="yyyy-MM-dd"-->
<!--                                                placeholder="出生日期"-->
<!--                                                v-model="emp.birthday"-->
<!--                                                style="width: 164px;"-->
<!--                                                size="mini">-->

<!--                                </el-date-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="政治面貌" prop="politicId">-->
<!--                                <el-select v-model="emp.politicId" placeholder="政治面貌" size="mini" style="width: 200px;">-->
<!--                                    <el-option-->
<!--                                            v-for="item in politicsstatus"-->
<!--                                            :key="item.id"-->
<!--                                            :label="item.name"-->
<!--                                            :value="item.id">-->
<!--                                    </el-option>-->
<!--                                </el-select>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="民族" prop="nationId">-->
<!--                                <el-select v-model="emp.nationId" placeholder="民族" size="mini" style="width: 180px">-->
<!--                                    <el-option v-for="item in nations"-->
<!--                                               :key="item.id"-->
<!--                                               :label="item.name"-->
<!--                                               :value="item.id">-->
<!--                                    </el-option>-->
<!--                                </el-select>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="5">-->
<!--                            <el-form-item label="籍贯" prop="nativePlace">-->
<!--                                <el-input v-model="emp.nativePlace"-->
<!--                                          prefix-icon="el-icon-edit"-->
<!--                                          placeholder="请输入籍贯"-->
<!--                                          size="mini"-->
<!--                                          style="width: 150px">-->
<!--                                </el-input>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="电子邮箱" prop="email">-->
<!--                                <el-input v-model="emp.email" prefix-icon="el-icon-message"-->
<!--                                          placeholder="请输入电子邮箱" size="mini" style="width: 164px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="联系地址" prop="address">-->
<!--                                <el-input v-model="emp.address" prefix-icon="fa fa-address-card-o"-->
<!--                                          placeholder="请输入联系地址" size="mini" style="width: 200px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="职位" prop="posId">-->
<!--                                <el-select v-model="emp.posId" placeholder="请选择职位" size="mini" style="width: 180px">-->
<!--                                    <el-option v-for="item in positions"-->
<!--                                               :key="item.id"-->
<!--                                               :label="item.name"-->
<!--                                               :value="item.id">-->
<!--                                    </el-option>-->
<!--                                </el-select>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="5">-->
<!--                            <el-form-item label="职称" prop="jobLevelId">-->
<!--                                <el-select v-model="emp.jobLevelId" placeholder="请选择职称" size="mini"-->
<!--                                           style="width: 150px">-->
<!--                                    <el-option v-for="item in joblevels"-->
<!--                                               :key="item.id"-->
<!--                                               :label="item.name"-->
<!--                                               :value="item.id">-->
<!--                                    </el-option>-->
<!--                                </el-select>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="所属部门" prop="departmentId">-->
<!--                                <el-popover-->
<!--                                        placement="bottom"-->
<!--                                        title="请选择部门"-->
<!--                                        width="200"-->
<!--                                        trigger="manual"-->
<!--                                        v-model="visible">-->
<!--                                    <el-tree default-expand-all-->
<!--                                             :data="allDeps"-->
<!--                                             :props="defaultProps"-->
<!--                                             @node-click="handleNodeClick"-->
<!--                                             style="width: 210px">-->
<!--                                    </el-tree>-->
<!--                                    <div slot="reference"-->
<!--                                         style="width: 163px; display: inline-flex; border: 1px solid #dedede;-->
<!--                                                height: 26px; border-radius:5px; cursor: pointer;align-items: center;-->
<!--                                                font-size: 12px;padding-left: 8px;box-sizing: border-box"-->
<!--                                         @click="showDepView">-->
<!--                                        {{inputDepName}}-->
<!--                                    </div>-->
<!--                                </el-popover>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="电话号码" prop="phone">-->
<!--                                <el-input v-model="emp.phone" prefix-icon="el-icon-phone" placeholder="请输入电话号码"-->
<!--                                          size="mini" style="width: 200px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="工号" prop="workId">-->
<!--                                <el-input v-model="emp.workID" prefix-icon="el-icon-user-solid"-->
<!--                                          disabled size="mini" style="width: 180px"-->
<!--                                          aria-placeholder="请输入工号"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="5">-->
<!--                            <el-form-item label="学历" prop="tiptopDegree">-->
<!--                                <el-select v-model="emp.tiptopDegree" placeholder="学历" size="mini" style="width: 150px">-->
<!--                                    <el-option v-for="item in tiptopDegrees"-->
<!--                                               :key="item"-->
<!--                                               :label="item"-->
<!--                                               :value="item">-->
<!--                                    </el-option>-->
<!--                                </el-select>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="毕业院校" prop="school">-->
<!--                                <el-input v-model="emp.school" prefix-icon="el-icon-edit"-->
<!--                                          placeholder="请输入毕业院校" size="mini" style="width: 164px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="专业名称" prop="specialty">-->
<!--                                <el-input v-model="emp.specialty" prefix-icon="el-icon-edit"-->
<!--                                          placeholder="请输入专业名称" size="mini" style="width: 200px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="入职日期" prop="beginDate">-->
<!--                                <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="入职日期"-->
<!--                                                v-model="emp.beginDate"-->
<!--                                                style="width: 152px;"-->
<!--                                                size="mini">-->
<!--                                </el-date-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="5">-->
<!--                            <el-form-item label="转正日期" prop="conversionTime">-->
<!--                                <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="转正日期"-->
<!--                                                v-model="emp.conversionTime"-->
<!--                                                style="width: 122px;"-->
<!--                                                size="mini">-->
<!--                                </el-date-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="6">-->
<!--                            <el-form-item label="合同起始日期" prop="beginContract">-->
<!--                                <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="合同起始日期"-->
<!--                                                v-model="emp.beginContract"-->
<!--                                                style="width: 136px;"-->
<!--                                                size="mini">-->
<!--                                </el-date-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="合同截止日期" prop="endContract">-->
<!--                                <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="合同截止日期"-->
<!--                                                v-model="emp.endContract"-->
<!--                                                style="width: 172px;"-->
<!--                                                size="mini">-->
<!--                                </el-date-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-col :span="7">-->
<!--                            <el-form-item label="身份证号" prop="idCard">-->
<!--                                <el-input v-model="emp.idCard" placeholder="请输入身份证号" prefix-icon="fa fa-address-card-o"-->
<!--                                          size="mini" style="width: 152px"/>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="9">-->
<!--                            <el-form-item label="聘用形式：" prop="engageForm">-->
<!--                                <el-radio-group v-model="emp.engageForm" style="margin-top: 8px">-->
<!--                                    <el-radio label="劳动合同"></el-radio>-->
<!--                                    <el-radio label="劳务合同"></el-radio>-->
<!--                                </el-radio-group>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                        <el-col :span="8">-->
<!--                            <el-form-item label="婚姻状况：" prop="wedlock">-->
<!--                                <el-radio-group v-model="emp.wedlock" style="margin-top: 8px">-->
<!--                                    <el-radio label="已婚"></el-radio>-->
<!--                                    <el-radio label="未婚"></el-radio>-->
<!--                                    <el-radio label="离异"></el-radio>-->
<!--                                </el-radio-group>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                </el-form>-->
<!--            </div>-->
<!--            <span slot="footer" class="dialog-footer">-->
<!--                <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--                <el-button type="primary" @click="doAddEmp">确 定</el-button>-->
<!--            </span>-->
<!--        </el-dialog>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->
<!--    export default {-->
<!--        name: "EmpBasic",-->
<!--        data() {-->
<!--            return {-->
<!--                searchValue: {-->
<!--                    politicId: null,-->
<!--                    nationId: null,-->
<!--                    posId: null,-->
<!--                    jobLevelId: null,-->
<!--                    engageForm: '',-->
<!--                    departmentId: null,-->
<!--                    beginDateScope: null-->
<!--                },-->
<!--                showAdvanceSearchVisible: false,-->
<!--                headers: {-->
<!--                    Authorization: window.sessionStorage.getItem('tokenStr')-->
<!--                },-->
<!--                importDataDisabled: false,-->
<!--                importDataBtnText: '导入数据',-->
<!--                importDataIcon: 'el-icon-upload2',-->
<!--                title: '',-->
<!--                defaultProps: {-->
<!--                    children: 'children',-->
<!--                    label: 'name'-->
<!--                },-->
<!--                inputDepName: '',-->
<!--                allDeps: [],-->
<!--                visible: false,-->
<!--                visible2: false,-->
<!--                emps: [],-->
<!--                loading: false,-->
<!--                total: 0,-->
<!--                currentPage: 1,-->
<!--                size: 10,-->
<!--                empName: [],-->
<!--                dialogVisible: false,-->
<!--                nations: [],-->
<!--                joblevels: [],-->
<!--                politicsstatus: [],-->
<!--                positions: [],-->
<!--                tiptopDegrees: ['博士', '硕士', '本科', '大专', '高中', '初中', '小学', '其他'],-->
<!--                emp: {-->
<!--                    id: null,-->
<!--                    name: '',-->
<!--                    gender: '',-->
<!--                    birthday: '',-->
<!--                    idCard: '',-->
<!--                    wedlock: '',-->
<!--                    nationId: null,-->
<!--                    nativePlace: '',-->
<!--                    politicId: null,-->
<!--                    email: '',-->
<!--                    phone: '',-->
<!--                    address: '',-->
<!--                    departmentId: null,-->
<!--                    jobLevelId: null,-->
<!--                    posId: null,-->
<!--                    engageForm: '',-->
<!--                    tiptopDegree: '',-->
<!--                    specialty: '',-->
<!--                    school: '',-->
<!--                    beginDate: '',-->
<!--                    workState: '在职',-->
<!--                    workID: '',-->
<!--                    contractTerm: null,-->
<!--                    conversionTime: '',-->
<!--                    notWorkDate: null,-->
<!--                    beginContract: '',-->
<!--                    endContract: '',-->
<!--                    workAge: null,-->
<!--                    salaryId: null-->
<!--                },-->
<!--                rules: {-->
<!--                    name: [{required: true, message: '请输入员工姓名', trigger: 'blur'}],-->
<!--                    gender: [{required: true, message: "请选择员工性别", trigger: "change"}],-->
<!--                    birthday: [{required: true, message: "请输入出生日期", trigger: "change"}],-->
<!--                    idCard: [{required: true, message: "请输入身份证号", trigger: "change"},-->
<!--                        {-->
<!--                            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/,-->
<!--                            message: "身份证号格式不正确",-->
<!--                            trigger: "change"-->
<!--                        }-->
<!--                    ],-->
<!--                    wedlock: [{required: true, message: "请选择婚姻状况", trigger: "change"}],-->
<!--                    nationId: [{required: true, message: "请选择民族", trigger: "change"}],-->
<!--                    nationPlace: [{required: true, message: "请输入籍贯", trigger: "change"}],-->
<!--                    politicId: [{required: true, message: "请选择政治面貌", trigger: "change"}],-->
<!--                    email: [{required: true, message: "请输入邮箱", trigger: "blur"}, {-->
<!--                        type: 'email',-->
<!--                        message: '邮箱格式不正确',-->
<!--                        trigger: 'blur'-->
<!--                    }],-->
<!--                    phone: [{required: true, message: "请输入电话号码", trigger: "change"}],-->
<!--                    address: [{required: true, message: "请输入联系地址", trigger: "change"}],-->
<!--                    departmentId: [{required: true, message: "请选择部门", trigger: "change"}],-->
<!--                    jobLevelId: [{required: true, message: "请选择职称", trigger: "change"}],-->
<!--                    posId: [{required: true, message: "请选择职位", trigger: "change"}],-->
<!--                    engageForm: [{required: true, message: "请选择聘用形式", trigger: "change"}],-->
<!--                    tiptopDegree: [{required: true, message: "请选择学历", trigger: "change"}],-->
<!--                    specialty: [{required: true, message: "请输入所属专业", trigger: "change"}],-->
<!--                    school: [{required: true, message: "请输入毕业院校", trigger: "change"}],-->
<!--                    beginDate: [{required: true, message: "请输入入职日期", trigger: "change"}],-->
<!--                    conversionTime: [{required: true, message: "请输入转正日期", trigger: "change"}],-->
<!--                    beginContract: [{required: true, message: "请输入合同起始日期", trigger: "change"}],-->
<!--                    endContract: [{required: true, message: "请输入合同截止日期", trigger: "change"}]-->
<!--                }-->
<!--            }-->
<!--        },-->
<!--        mounted() {-->
<!--            this.initEmps();-->
<!--            this.initData();-->
<!--            this.initPositions();-->
<!--        },-->
<!--        methods: {-->
<!--            searchHandleNodeClick(data) {-->
<!--                this.inputDepName = data.name;-->
<!--                this.searchValue.departmentId = data.id;-->
<!--                this.visible2 = !this.visible2;-->
<!--            },-->
<!--            onSuccess() {-->
<!--                this.importDataIcon = 'el-icon-upload2';-->
<!--                this.importDataBtnText = '导入数据';-->
<!--                this.importDataDisabled = false;-->
<!--                this.initEmps();-->
<!--            },-->
<!--            onError() {-->
<!--                this.importDataIcon = 'el-icon-upload2';-->
<!--                this.importDataBtnText = '导入数据';-->
<!--                this.importDataDisabled = false;-->
<!--            },-->
<!--            beforeUpload() {-->
<!--                this.importDataIcon = 'el-icon-loading';-->
<!--                this.importDataBtnText = '正在导入';-->
<!--                this.importDataDisabled = true;-->
<!--            },-->
<!--            exportData() {-->
<!--                this.downloadRequest('/employee/basic/export');-->
<!--            },-->
<!--            showEditEmpView(data) {-->
<!--                this.title = '编辑员工信息';-->
<!--                this.dialogVisible = true;-->
<!--                this.emp = data;-->
<!--                this.initPositions();-->
<!--                this.inputDepName = data.department.name;-->
<!--            },-->
<!--            deleteEmp(data) {-->
<!--                this.$confirm('此操作将永久删除 ' + data.name + ', 是否继续?', '提示', {-->
<!--                    confirmButtonText: '确定',-->
<!--                    cancelButtonText: '取消',-->
<!--                    type: 'warning'-->
<!--                }).then(() => {-->
<!--                    this.deleteRequest('/employee/basic/' + data.id).then(resp => {-->
<!--                        if (resp) {-->
<!--                            this.initEmps();-->
<!--                        }-->
<!--                    })-->
<!--                }).catch(() => {-->
<!--                    this.$message({-->
<!--                        type: 'info',-->
<!--                        message: '已取消删除'-->
<!--                    });-->
<!--                });-->
<!--            },-->
<!--            doAddEmp() {-->
<!--                if (this.emp.id) {-->
<!--                    this.$refs['empForm'].validate(valid => {-->
<!--                        if (valid) {-->
<!--                            this.putRequest('/employee/basic/', this.emp).then(resp => {-->
<!--                                if (resp) {-->
<!--                                    this.dialogVisible = false;-->
<!--                                    this.initEmps();-->
<!--                                }-->
<!--                            })-->
<!--                        }-->
<!--                    })-->
<!--                } else {-->
<!--                    this.$refs['empForm'].validate(valid => {-->
<!--                        if (valid) {-->
<!--                            this.postRequest('/employee/basic/', this.emp).then(resp => {-->
<!--                                if (resp) {-->
<!--                                    this.dialogVisible = false;-->
<!--                                    this.initEmps();-->
<!--                                }-->
<!--                            })-->
<!--                        }-->
<!--                    })-->
<!--                }-->
<!--            },-->
<!--            handleNodeClick(data) {-->
<!--                this.inputDepName = data.name;-->
<!--                this.emp.departmentId = data.id;-->
<!--                this.visible = !this.visible;-->
<!--            },-->
<!--            showDepView() {-->
<!--                this.visible = !this.visible;-->
<!--            },-->
<!--            showDepView2() {-->
<!--                this.visible2 = !this.visible2;-->
<!--            },-->
<!--            getMaxWorkID() {-->
<!--                this.getRequest('/employee/basic/maxWorkID').then(resp => {-->
<!--                    if (resp) {-->
<!--                        this.emp.workID = resp.obj;-->
<!--                    }-->
<!--                })-->
<!--            },-->
<!--            initPositions() {-->
<!--                this.getRequest('/employee/basic/positions').then(resp => {-->
<!--                    if (resp) {-->
<!--                        this.positions = resp;-->
<!--                    }-->
<!--                })-->
<!--            },-->
<!--            initData() {-->
<!--                if (!window.sessionStorage.getItem("nations")) {-->
<!--                    this.getRequest('/employee/basic/nations').then(resp => {-->
<!--                        if (resp) {-->
<!--                            this.nations = resp;-->
<!--                            window.sessionStorage.setItem('nations', JSON.stringify(resp));-->
<!--                        }-->
<!--                    })-->
<!--                } else {-->
<!--                    this.nations = JSON.parse(window.sessionStorage.getItem('nations'));-->
<!--                }-->
<!--                if (!window.sessionStorage.getItem("joblevels")) {-->
<!--                    this.getRequest('/employee/basic/joblevels').then(resp => {-->
<!--                        if (resp) {-->
<!--                            this.joblevels = resp;-->
<!--                            window.sessionStorage.setItem('joblevels', JSON.stringify(resp));-->
<!--                        }-->
<!--                    })-->
<!--                } else {-->
<!--                    this.joblevels = JSON.parse(window.sessionStorage.getItem('joblevels'));-->
<!--                }-->
<!--                if (!window.sessionStorage.getItem("politicsstatus")) {-->
<!--                    this.getRequest('/employee/basic/politicsstatus').then(resp => {-->
<!--                        if (resp) {-->
<!--                            this.politicsstatus = resp;-->
<!--                            window.sessionStorage.setItem('politicsstatus', JSON.stringify(resp));-->
<!--                        }-->
<!--                    })-->
<!--                } else {-->
<!--                    this.politicsstatus = JSON.parse(window.sessionStorage.getItem('politicsstatus'));-->
<!--                }-->
<!--                if (!window.sessionStorage.getItem("allDeps")) {-->
<!--                    this.getRequest('/employee/basic/deps').then(resp => {-->
<!--                        if (resp) {-->
<!--                            this.allDeps = resp;-->
<!--                            window.sessionStorage.setItem('allDeps', JSON.stringify(resp));-->
<!--                        }-->
<!--                    })-->
<!--                } else {-->
<!--                    this.allDeps = JSON.parse(window.sessionStorage.getItem('allDeps'));-->
<!--                }-->
<!--            },-->
<!--            sizeChange(size) {-->
<!--                this.size = size;-->
<!--                this.initEmps();-->
<!--            },-->
<!--            currentChange(currentPage) {-->
<!--                this.currentPage = currentPage;-->
<!--                this.initEmps();-->
<!--            },-->
<!--            showAddEmpView() {-->
<!--                this.title = '添加员工';-->
<!--                this.emp = {-->
<!--                    id: null,-->
<!--                    name: '',-->
<!--                    gender: '',-->
<!--                    birthday: '',-->
<!--                    idCard: '',-->
<!--                    wedlock: '',-->
<!--                    nationId: null,-->
<!--                    nativePlace: '',-->
<!--                    politicId: null,-->
<!--                    email: '',-->
<!--                    phone: '',-->
<!--                    address: '',-->
<!--                    departmentId: null,-->
<!--                    jobLevelId: null,-->
<!--                    posId: null,-->
<!--                    engageForm: '',-->
<!--                    tiptopDegree: '',-->
<!--                    specialty: '',-->
<!--                    school: '',-->
<!--                    beginDate: '',-->
<!--                    workID: '',-->
<!--                    contractTerm: null,-->
<!--                    conversionTime: '',-->
<!--                    notWorkDate: null,-->
<!--                    beginContract: '',-->
<!--                    endContract: '',-->
<!--                    workAge: null,-->
<!--                    salaryId: null-->
<!--                };-->
<!--                this.inputDepName = '';-->
<!--                this.getMaxWorkID();-->
<!--                this.initPositions();-->
<!--                this.dialogVisible = true;-->
<!--            },-->
<!--            initEmps(type) {-->
<!--                this.loading = true;-->
<!--                let url = '/employee/basic/?currentPage=' + this.currentPage + '&size=' + this.size;-->
<!--                if (type && type == 'advanced') {-->
<!--                    if (this.searchValue.politicId) {-->
<!--                        url += '&politicId=' + this.searchValue.politicId;-->
<!--                    }-->
<!--                    if (this.searchValue.nationId) {-->
<!--                        url += '&nationId=' + this.searchValue.nationId;-->
<!--                    }-->
<!--                    if (this.searchValue.posId) {-->
<!--                        url += '&posId=' + this.searchValue.posId;-->
<!--                    }-->
<!--                    if (this.searchValue.jobLevelId) {-->
<!--                        url += '&jobLevelId=' + this.searchValue.jobLevelId;-->
<!--                    }-->
<!--                    if (this.searchValue.engageForm) {-->
<!--                        url += '&engageForm=' + this.searchValue.engageForm;-->
<!--                    }-->
<!--                    if (this.searchValue.departmentId) {-->
<!--                        url += '&departmentId=' + this.searchValue.departmentId;-->
<!--                    }-->
<!--                    if (this.searchValue.beginDateScope) {-->
<!--                        url += '&beginDateScope=' + this.searchValue.beginDateScope;-->
<!--                    }-->
<!--                } else {-->
<!--                    url += '&name=' + this.empName;-->
<!--                }-->
<!--                this.getRequest(url).then(resp => {-->
<!--                    this.loading = false;-->
<!--                    if (resp) {-->
<!--                        this.emps = resp.data;-->
<!--                        this.total = resp.total;-->
<!--                    }-->
<!--                })-->
<!--            }-->
<!--        }-->
<!--    }-->
<!--</script>-->

<!--<style>-->
<!--    /* 可以设置不同的进入和离开动画 */-->
<!--    /* 设置持续时间和动画函数 */-->
<!--    .slide-fade-enter-active {-->
<!--        transition: all .8s ease;-->
<!--    }-->

<!--    .slide-fade-leave-active {-->
<!--        transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);-->
<!--    }-->

<!--    .slide-fade-enter, .slide-fade-leave-to-->
<!--        /* .slide-fade-leave-active for below version 2.1.8 */-->
<!--    {-->
<!--        transform: translateX(10px);-->
<!--        opacity: 0;-->
<!--    }-->

<!--</style>-->
