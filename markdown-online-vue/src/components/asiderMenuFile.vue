<template>
<div>
   <div v-for="(item,indexs) in item" :key="indexs">

            <div @click="clickFile(item,path)" v-if="typeof(item)=='string'" >
                
               <el-menu-item index="1" >{{item}}
               </el-menu-item>
            </div>
        <!-- +''是为了让indexs变为字符串类型 -->
          <el-submenu v-else :index="indexs+''" v-for="(item,index) in item" :key="index">
               <template slot="title">
          <i class="el-icon-notebook-1"></i>
                   <span ref="fileDir">{{index}}
                   </span>
          </template>
                    <!-- 递归 -->
                <asiderMenu :item="item" :path="path+'/'+index"/>
        </el-submenu>
            
              
                
            
        </div>
        
</div>
    
</template>

<script>
// import asiderMenu from '../components/asiderMenuFile.vue'
export default {
    name:"asiderMenu"
            ,data(){
        return{
            markPath:[]
        }
       
    },
    props:{
        item:{
            type:Array,
            required:false
        },
        path:{
            type:String,
            required:false
        }
    },created(){

    },methods:{
        clickFile:function (item,path){
            // console.log(item,path+"/"+item)
            this.markPath=path+"/"+item
            sessionStorage.setItem("curPath",this.markPath);
            // console.log(this.$refs['fileDir'])
            //  sessionStorage.setItem("currentSingerId", JSON.stringify(this.markPath));
            sessionStorage.setItem("clickType","1");
        this.$router.push({
          
        path:'/Notes',
          query: {
            id: this.markPath
          }
        });
    },updated(){

    }
        
        }
}
</script>


