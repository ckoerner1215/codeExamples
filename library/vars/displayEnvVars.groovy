#!/usr/bin/env groovy


def call() {
	echo sh(script: 'env|sort', returnStdout: true)
}