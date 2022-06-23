<template>
  <div class="about">
    <el-container>
            <el-aside width="200px" >

       
        <el-row class="tac">
  
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
      <el-submenu index="10086">
        <template slot="title">
          <i class="el-icon-notebook-1"></i>
          <span>Markdown</span>
        </template>

      <!-- 笔记本目录 -->
      <div  v-for="(item,index) in allFile" :key="index">
        <!-- <div v-for="(item,indexs) in item" :key="indexs">

            <div v-if="typeof(item)=='string'">
               <el-menu-item index="1">{{item}}文件</el-menu-item>
            </div>

          <el-submenu v-else :index="indexs" v-for="(item,index) in item" :key="index">
               <template slot="title">
          <i class="el-icon-notebook-1"></i>
             {{index}}
          </template>                                                             
        </el-submenu>               
        </div> -->
        <asider-menu :item="item" :path="index" />
      </div>

        
  
      </el-submenu>

    </el-menu>
        </el-row>


 
            </el-aside>
    <!-- 右边显示 -->
    <el-container>
       
        <el-header>
        <div class="header-left">
            <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/Notes/newFile'}">首页</el-breadcrumb-item>
    <el-breadcrumb-item><a>新笔记</a></el-breadcrumb-item>
    </el-breadcrumb>
        </div>
        <div class="header-right">
          <span v-show="false">{{this.$route.query.id}}</span>
        <el-button type="primary" size="mini" @click="open">分享</el-button>
    <el-dropdown class="moreButton">
            <i class="el-icon-more-outline" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item>查看</el-dropdown-item> -->
            <el-dropdown-item type="text" @click.native="dialogVisible = true" >新增</el-dropdown-item>
            <el-dropdown-item @click.native="delFile">删除</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <!-- 对话框 -->
              <el-dialog
        title="请输入文件名"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
       <el-form ref="form" :model="form" label-width="80px">
        
        <el-form-item >
    <el-input type="text" v-model="form.path" ></el-input>
  </el-form-item>
       
       <el-form-item>
    <el-button type="primary" @click="setFile">立即创建</el-button>
    <el-button @click="dialogVisible = false">取消</el-button>
  </el-form-item>
      
        
       </el-form>

          

        
      </el-dialog>

        </div>
        </el-header>
        <el-main >
        <router-view  :key="$route.fullPath"></router-view>

        </el-main>
</el-container>
</el-container>


  </div>
</template>





<script>
import asiderMenu from '@/components/asiderMenuFile.vue'

  export default {
    components:{
        asiderMenu
    },
    // ...
    data() {
      return {
        form:{
          path:''
        },
        dialogVisible: false,
        filePath:'',
        allFile:[],
        judgeMenu:[],
        item:[],
        path:""
    }
    },methods: {
      handleOpen(key, keyPath,i) {
          sessionStorage.setItem("clickType","0");
        console.log(key, keyPath,i);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
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
      },
      getAllFile(){
        this.axios.get("/doc/DocsPathsJson?username="+sessionStorage.getItem("username")).then(res=>{
          this.allFile=res.data.data;
          console.log(this.allFile)
        })
           
      }, 
     
         judgeFile:function (){
                for(const i in this.allFile){
                    if(typeof(this.allFile[i])=="string"){
                        console.log(this.allFile[i]+"是文件！")
                    }else{
                        console.log(getDirName(this.allFile[i])+"是目录！")
                    }
                }
            },
            delFile:function(){
              this.filePath=this.$route.query.id
                let curPath = sessionStorage.getItem("curPath");
              if (sessionStorage.getItem("clockType")=="0"){
                  let split = curPath.split("/");
                  let filePath = split.pop();
              }
              console.log(this.filePath)
               this.axios.delete("/doc/delFile?path="+ this.filePath).then(res => {
                 this.$router.push({
                    path:'/Notes/newFile',
                    });
                    this.getAllFile();
                    this.$forceUpdate();

                   alert("删除成功")
                })
                },
      //            handleClose(done) {
      //   this.$confirm('确认关闭？')
      //     .then(_ => {
      //       done();
      //     })
      //     .catch(_ => {});
      // },
          setFile:function(){
              let curPath=sessionStorage.getItem("curPath")
              let split = curPath.split("/");
              split.pop();
              console.log(split);
              let curDir="";
              for (let i in split) {
                  if (i==0){
                      curDir+=split[i];
                  }else{
                      curDir+="/"+split[i];
                  }
              }
              if(this.form.path.indexOf(".")>0){
                  this.axios.put("/doc/addDoc?path="+ curDir+"/"+this.form.path).then(res => {
                      this.getAllFile();
                      this.dialogVisible = false
                  })
              }else{
                  this.axios.put("/doc/addDir?path="+ curDir+"/"+this.form.path).then(res => {
                      this.getAllFile();
                      this.dialogVisible = false
                  })
              }

              // sessionStorage.getItem("curPath");
          // alert("点击")
      }
    

    },created(){
      this.getAllFile();
      // this.judgeFile();
     
           
      
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
    overflow: hidden;
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