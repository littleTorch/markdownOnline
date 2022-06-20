<template>
  <div class="about">
    <el-container>
            <el-aside width="200px" >

<router-link to="/Notes/note">笔记本</router-link>

            </el-aside>
    <!-- 右边显示 -->
    <el-container>
       
        <el-header>
        <div class="header-left">
            <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/Notes/newFile' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/">新笔记</a></el-breadcrumb-item>
    </el-breadcrumb>
        </div>
        <div class="header-right">


        <el-button type="primary" size="mini" @click="open">分享</el-button>
    <el-dropdown class="moreButton">
            <i class="el-icon-more-outline" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        </div>
        </el-header>
        <el-main >
        <router-view></router-view>

        </el-main>
</el-container>
</el-container>


  </div>
</template>





<script>


  export default {
    components:{

    },
    // ...
    data() {
      return {
    }
    },methods: {
      open() {
        this.$alert('这是一段内容', '标题名称', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      }
    }
    
  }
</script>

<style scoped>

html,body{
    height: 100%;
}
  .about{
    max-width: 1200px;
  min-width: 900px;
  margin: 2px auto 0;
  }
  .el-header, .el-footer {
    background-color: #fff;
    color: #333;
    text-align: center;
    line-height: 60px;
     box-shadow: 4px 3px 5px #ccc;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    height:600px;
  }



  .el-main {
    color: #333;
    text-align: center;
     box-shadow: 7px 8px 10px #ccc;
  }
.header-left{
  float: left;
}
.header-right{
  float: right;
 
}
.el-breadcrumb{
  line-height: 60px;
}
.moreButton{
  margin-left: 25px;
}
 a{
      text-decoration: none;
  }


 
</style>