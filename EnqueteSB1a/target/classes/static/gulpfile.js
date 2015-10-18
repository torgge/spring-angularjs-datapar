// instanciando módulos
// $ npm install --save-dev gulp
// $ npm install --save-dev gulp-cssmin
// $ npm install --save-dev gulp-util
// $ npm install --save-dev gulp-ugligy
// $ npm install --save-dev gulp-cssmin 
// $ npm install --save-dev gulp-watch 
// $ npm install --save-dev gulp-concat 
// $ npm install --save-dev gulp-strip-css-comments

var js = [
      	"js/jquery.js",
    	"js/angular.js",
    	"js/angular-animate.js",
    	"js/angular-sanitize.js",
    	"js/angular-touch.js",
    	"js/angular-resource.js",
    	"js/moment.js",
    	"js/ui-grid.js",
    	"js/daterangepicker.js",
    	"js/angular-daterangepicker.min.js",
    	"js/select.js",
    	"js/nya-bs-select.js"
        ];

//
var gulp = require('gulp');

//Remove comentários CSS
var stripCssComments = require('gulp-strip-css-comments');

//
var gutil = require('gulp-util');

//Transforma o javascript em formato ilegível para humanos
var uglify = require('gulp-uglify');

//
var watch = require('gulp-watch');

//Minifica o CSS
var cssmin = require("gulp-cssmin");

//Agrupa todos os arquivos em um
var concat = require("gulp-concat");

//tarefas

gulp.task('build-js', function() {
    return gulp
            .src(js)
            .pipe(uglify())
            .pipe(gulp.dest('build/js'));      
});

gulp.task('build-css', function(){
    gulp.src(['css/*.css'])
    .pipe(stripCssComments({all: true}))
    .pipe(cssmin())
    .pipe(gulp.dest('build/css/'));
});


gulp.task('concat-css', function(){
    gulp.src(['css/*.css'])
    .pipe(concat('app.min.css'))
    .pipe(stripCssComments({all: true}))
    .pipe(cssmin())
    .pipe(gulp.dest('css/'));
});


//Tarefa de minificação do Javascript
gulp.task('concat-js', function () {
    gulp.src(js)            // Arquivos que serão carregados, veja variável 'js' no início
    .pipe(concat('app.min.js'))      // Arquivo único de saída
    .pipe(uglify())                  // Transforma para formato ilegível
    .pipe(gulp.dest('./js/'));       // pasta de destino do arquivo(s)
});

 

//Cria a TASK de verificar em tempo real alterações, 
//se detectar alguma alteração, será processado o comando relativo ao arquivo
gulp.task('watch-css', function() {
	gulp.watch(['css/*.css'], ['minify-css']);
});
