
var path = require('path');

var gulp = require('gulp');

// Gulp modules
var gutil = require('gulp-util');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var del = require('del');
var merge = require('merge-stream');
var minifyCSS = require('gulp-minify-css');
var imagemin = require('gulp-imagemin');
var replace = require('gulp-replace');
var plumber = require('gulp-plumber');
var templateCache = require('gulp-angular-templatecache');


// The path of the bower_components folder
var BOWER_PATH = path.join(__dirname, "bower_components");

// The path of the output folders
var BUILD_BASE_PATH = path.join(__dirname, '../build/');
var BUILD_JS_PATH = path.join(BUILD_BASE_PATH, '/js');
var BUILD_CSS_PATH = path.join(BUILD_BASE_PATH, '/css');
var BUILD_IMG_PATH = path.join(BUILD_BASE_PATH, '/img');
var BUILD_TEMPLATE_PATH = path.join(BUILD_BASE_PATH, '/js');

var PATHS = {
    scripts: ['src/**/*.js'],
    external_scripts: [
        path.join(BOWER_PATH, 'jquery/dist/jquery.min.js'),
        path.join(BOWER_PATH, 'bootstrap/dist/js/bootstrap.min.js'),
        path.join(BOWER_PATH, 'angular/angular.min.js'),
        path.join(BOWER_PATH, 'angular-route/angular-route.min.js')
    ],
    styles: [
        'css/**/*.css',
        path.join(BOWER_PATH, 'bootstrap/dist/css/bootstrap.css')
    ],
    images: [
        'img/**/*.png',
        'img/**/*.gif',
        'img/**/*.jpg'
    ],
    templates : [
        'partials/**/*.html'
    ]
};

var onError = function (err) {
    gutil.beep();
    console.log(err.stack);
};

gulp.task('clean', function (cb) {
    del([BUILD_BASE_PATH], {force: true}, cb);
});

gulp.task('scripts', ['clean'], function () {
    var extScripts = gulp.src(PATHS.external_scripts);

    var appScripts = gulp.src(PATHS.scripts)
        .pipe(plumber({
            errorHandler: onError
        }))
        .pipe(uglify());
        
    return merge(extScripts, appScripts)
        .pipe(concat('all.min.js'))
        .pipe(gulp.dest(BUILD_JS_PATH));
});

gulp.task('css', ['clean'], function () {
    return gulp.src(PATHS.styles)
        .pipe(minifyCSS())
        .pipe(concat('all.min.css'))
        .pipe(gulp.dest(BUILD_CSS_PATH));
});

// Copy all static images
gulp.task('images', ['clean'], function() {
    return gulp.src(PATHS.images)
        .pipe(imagemin())
        .pipe(gulp.dest(BUILD_IMG_PATH));
});

gulp.task('templates', ['clean', 'scripts'], function() {
    return gulp.src(PATHS.templates)
        .pipe(templateCache({
            module: 'movieFinder.templates'
        }))
        .pipe(gulp.dest(BUILD_TEMPLATE_PATH));
});

// The default task (called when you run `gulp` from cli)
gulp.task('default', ['scripts', 'css', 'images', 'templates']);
