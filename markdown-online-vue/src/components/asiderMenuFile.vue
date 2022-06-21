<template>
<div>
   <div v-for="(item,indexs) in item" :key="indexs">

                <div v-if="typeof(item)=='string'">
               <el-menu-item index="1">{{item}}文件</el-menu-item>
            </div>
        <!-- +''是为了让indexs变为字符串类型 -->
          <el-submenu v-else :index="indexs+''" v-for="(item,index) in item" :key="index">
               <template slot="title">
          <i class="el-icon-notebook-1"></i>
             {{index}}
          </template>
                    <!-- 递归 -->
                <asiderMenu :item="item" />     
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
        }
    },created(){
    }
}
</script>


