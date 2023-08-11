<template>
    <div>
        <el-tabs v-model="activeName" type="card">
            <el-tab-pane label="部门管理" name="DepMana"><DepMana></DepMana></el-tab-pane>
            <el-tab-pane label="职位管理" name="PosMana"><PosMana></PosMana></el-tab-pane>
            <el-tab-pane label="职称管理" name="JoblevelMana"><JoblevelMana></JoblevelMana></el-tab-pane>
            <el-tab-pane label="奖惩规则" name="EcMana"><EcMana></EcMana></el-tab-pane>
            <el-tab-pane label="权限组" name="PermissMana"><PermissMana></PermissMana></el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import DepMana from '../../components/SystemBasic/DepMana'
    import EcMana from '../../components/SystemBasic/EcMana'
    import JoblevelMana from '../../components/SystemBasic/JoblevelMana'
    import PermissMana from '../../components/SystemBasic/PermissMana'
    import PosMana from '../../components/SystemBasic/PosMana'

    export default {
        name: "SysBasic",
        data(){
            return{
                activeName:'DepMana'
            }
        },
        //组件化管理
        components:{
            DepMana,
            EcMana,
            JoblevelMana,
            PermissMana,
            PosMana
        }
    }
</script>

<style scoped>

</style>
