from node:24-alpine

WORKDIR /app

#install pnpm globally
RUN npm i -g pnpm


COPY package.json pnpm-lock.yaml ./

RUN pnpm ci

COPY . .

EXPOSE 5173

CMD ["pnpm", "run", "dev"]
