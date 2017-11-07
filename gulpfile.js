/**
 * Created by lijingx on 11/7/2017.
 */
// 引入 gulp
var gulp = require('gulp');

// 引入组件
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var cssmin = require('gulp-minify-css');
var ngAnnotate = require('gulp-ng-annotate');

// 合并，压缩文件
gulp.task('loginjs', function() {
    gulp.src('./src/main/webapp/static/js/login.js')
        .pipe(rename('login.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/static/js'));
});

gulp.task('componentdatejs', function() {
    gulp.src('./src/main/webapp/static/metronic/pages/scripts/components-date-time-pickers.js')
        .pipe(rename('components-date-time-pickers.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/static/metronic/pages/scripts'));
});

gulp.task('notifyjs', function () {
    gulp.src('./src/main/webapp/static/notify/notify.js')
        .pipe(rename('notify.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/static/notify'));
});

gulp.task('appjs', function() {
    gulp.src('./src/main/webapp/app/app.js')
        .pipe(ngAnnotate())
        .pipe(rename('app.min.js'))

        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});
gulp.task('configjs', function() {
    gulp.src('./src/main/webapp/app/config.js')
        .pipe(ngAnnotate())
        .pipe(rename('config.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});
gulp.task('routerjs', function() {
    gulp.src('./src/main/webapp/app/router.js')
        .pipe(ngAnnotate())
        .pipe(rename('router.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});

gulp.task('controllersjs', function() {
    gulp.src('./src/main/webapp/app/controllers.js')
        .pipe(ngAnnotate())
        .pipe(rename('controllers.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});

gulp.task('filterjs', function() {
    gulp.src('./src/main/webapp/app/filter.js')
        .pipe(ngAnnotate())
        .pipe(rename('filter.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});
gulp.task('servicesjs', function() {
    gulp.src('./src/main/webapp/app/services.js')
        .pipe(ngAnnotate())
        .pipe(rename('services.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/main/webapp/app'));
});



gulp.task('componentscss', function () {
    gulp.src('./src/main/webapp/static/metronic/global/css/components.css')
        .pipe(rename('components.min.css'))
        .pipe(cssmin())
        .pipe(gulp.dest('./src/main/webapp/static/metronic/global/css'));
});

gulp.task('layoutcss', function () {
    gulp.src('./src/main/webapp/static/metronic/layouts/layout2/css/layout.css')
        .pipe(rename('layout.min.css'))
        .pipe(cssmin())
        .pipe(gulp.dest('./src/main/webapp/static/metronic/layouts/layout2/css'));
});

gulp.task('bluecss', function () {
    gulp.src('./src/main/webapp/static/metronic/layouts/layout2/css/themes/blue.css')
        .pipe(rename('blue.min.css'))
        .pipe(cssmin())
        .pipe(gulp.dest('./src/main/webapp/static/metronic/layouts/layout2/css/themes'));
});

gulp.task('notifycss', function () {
    gulp.src('./src/main/webapp/static/notify/notify.css')
        .pipe(rename('notify.min.css'))
        .pipe(cssmin())
        .pipe(gulp.dest('./src/main/webapp/static/notify'));
});


// 默认任务
gulp.task('runappjs', function(){
    gulp.run('appjs');
    gulp.run('configjs');
    gulp.run('routerjs');
    gulp.run('controllersjs');
    gulp.run('filterjs');
    gulp.run('servicesjs');

});

// 默认任务
gulp.task('default', function(){

    // 监听文件变化
    gulp.watch('./src/main/webapp/app/app.js', function(){
        gulp.run('appjs');
    });
    gulp.watch('./src/main/webapp/static/js/config.js', function(){
        gulp.run('configjs');
    });
    gulp.watch('./src/main/webapp/static/js/router.js', function(){
        gulp.run('routerjs');
    });
    gulp.watch('./src/main/webapp/static/js/controllers.js', function(){
        gulp.run('controllersjs');
    });
    gulp.watch('./src/main/webapp/static/js/filter.js', function(){
        gulp.run('filterjs');
    });
    gulp.watch('./src/main/webapp/static/js/services.js', function(){
        gulp.run('servicesjs');
    });
    gulp.watch('./src/main/webapp/static/js/login.js', function(){
        gulp.run('loginjs');
    });


});