<template>
<div>
   <div v-for="(item,indexs) in item" :key="indexs">

                <div @click="clickFile(item,path)" v-if="typeof(item)=='string'">
               <el-menu-item index="1">{{item}}文件</el-menu-item>
            </div>
        <!-- +''是为了让indexs变为字符串类型 -->
          <el-submenu v-else :index="indexs+''" v-for="(item,index) in item" :key="index">
               <template slot="title">
          <i class="el-icon-notebook-1"></i>
                   <span ref="fileDir">{{index}}</span>
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
            console.log(item,path+"/"+item)
            // console.log(this.$refs['fileDir'])
        }
    }
}
</script>


