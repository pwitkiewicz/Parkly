# Parkly Frontend

This repository holds the front end for the Parkly application. It was created using the `yarn` package manager.

## Installation of yarn

To check if `yarn` is installed on your computer, type `yarn --version`. If the command doesn't return version, use:

`npm install --global yarn`

**Very important**: to install dependencies in the project use:

`yarn install --frozen-lockfile`

Don't forget about the flag because otherwise lockfile may become damaged.

## UI library used in Parkly

In Parkly we're using the MUI UI library. You can read its documentation [here](https://mui.com). Styling engine is
Emotion. You can read about it [here](https://emotion.sh/docs/introduction).

## General project Information

The project tree is divided into two main directories:

- **public**: contains general files about web app
- **src**: contains source code

Directory **src** is divided into the following subdirectories:

- **app**: contains files regarding main `App.tsx` file. It's not necessary to add anything to this directory.
- **components**: contains subdirectories for each component with necessary files inside.
- **constants**: contains files that will be common for each component and page, for example, theme.
- **pages**: contains subdirectories for each page with necessary files inside.

More directories will be added later.

## Available Scripts

In the project directory, you can run:

### `yarn start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `yarn test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `yarn build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.
